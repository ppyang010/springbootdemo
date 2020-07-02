package com.code.demo;

import cn.hutool.json.JSONUtil;
import com.code.data.PeopleEntity;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author ccy
 * @description
 * @time 2020-07-01 10:40
 */
@Slf4j
public class GuavaDemo {


    private static ThreadPoolExecutor CACHE_EXECUTOR = new ThreadPoolExecutor(10, 10,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());

    private static ThreadPoolExecutor QUERY_EXECUTOR = new ThreadPoolExecutor(10, 10,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());
    private static LoadingCache<String, Optional<PeopleEntity>> GuavaCache = CacheBuilder.newBuilder()
            .maximumSize(30)
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .refreshAfterWrite(2, TimeUnit.SECONDS)
            .build(new CacheLoader<String, Optional<PeopleEntity>>() {

                @Override
                public Optional<PeopleEntity> load(String key) {
                    log.info("本地缓存无数据，加载数据");
                    return getDataLoad(key);
                }

                @Override
                public ListenableFuture<Optional<PeopleEntity>> reload(String key, Optional<PeopleEntity> old) {
                    log.info("异步刷新本地缓存数据(key={})", key);
                    ListenableFutureTask<Optional<PeopleEntity>> futureTask = ListenableFutureTask.create(() -> getDataReload(key));
                    CACHE_EXECUTOR.execute(futureTask);
                    return futureTask;
                }

                private Optional<PeopleEntity> getDataLoad(String key) {
                    log.info("getDataLoad");
                    PeopleEntity entity = new PeopleEntity();
                    entity.setName(key);
                    Random random = new Random();
                    entity.setAge(random.nextInt(100));
                    return Optional.ofNullable(entity);
                }


                private Optional<PeopleEntity> getDataReload(String key) {
                    log.info("getDataReload sleep");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("getDataReload sleep end");
                    PeopleEntity entity = new PeopleEntity();
                    entity.setName(key);
                    Random random = new Random();
                    entity.setAge(random.nextInt(100));
                    return Optional.ofNullable(entity);
                }


            });


    public static void main(String[] args) {

        for (int i = 0; i < 200; i++) {
            int finalI = i;
            QUERY_EXECUTOR.execute(() -> {
                try {
                    log.info("getGuavaCache i={}", finalI);
                    Optional<PeopleEntity> test = GuavaCache.get("test");
                    PeopleEntity peopleEntity = test.orElse(null);
                    log.info("getGuavaCache i={},res={}", finalI, JSONUtil.parse(peopleEntity));
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            });

            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        QUERY_EXECUTOR.shutdown();
        CACHE_EXECUTOR.shutdown();

    }


}
