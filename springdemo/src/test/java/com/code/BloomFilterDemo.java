package com.code;

import cn.hutool.core.math.MathUtil;
import cn.hutool.core.util.RandomUtil;
import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;

import java.util.Random;

public class BloomFilterDemo {

    /**
     * 创建过滤器，需要实现接口类Funnel将数据进行转化。
     * 使用 BloomFilter.put() 方法，可以将数据存放其中。(非线程安全)
     * 使用 BloomFilter.mightContain()方法，判断元素是否存在于过滤器之中。(非线程安全)
     */
    public static void main(String[] args) {
        // 1. 创建符合条件的布隆过滤器
        BloomFilter<String> filter = BloomFilter.create(new Funnel<String>() {
            @Override
            public void funnel(String from, PrimitiveSink into) {
                into.putString(from, Charsets.UTF_8);
            }
        }, 10000, 0.0001);

        //2. 将一部分数据添加进去
        for (int index = 0; index < 10000; index++) {
            String randomStr = "abc_test_" + RandomUtil.randomInt(0, 10000);
            filter.put(randomStr);
        }
        System.out.println("write all...");
        int order = 0;
        // 3. 测试结果
        for (int i = 0; i < 10000; i++) {
            String randomStr = "abc_test_" + RandomUtil.randomInt(0, 10000);
            if (filter.mightContain(randomStr)) {
                System.out.println("order=" + order + "  " + randomStr + " exist");
                order++;
            }
        }
    }
}
