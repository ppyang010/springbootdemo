package com.code.auth.domain;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.*;

/**
 * 通用返回实体类
 * @author ccy
 */
public class PlatformReturn implements Serializable{
    public static final int INITIAL_CAPACITY = 10;
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private boolean success;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String message = "";
    private int errorCode = 0;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String traceCode = "";
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, Object> results = null;


    public PlatformReturn() {
    }

    public PlatformReturn(boolean success) {
        this.success = success;
    }

    public PlatformReturn(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public PlatformReturn(boolean success, String message, int errorCode) {
        this.success = success;
        this.message = message;
        this.errorCode = errorCode;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static PlatformReturn success() {
        return new PlatformReturn(true);
    }

    public static PlatformReturn failure(String message) {
        return new PlatformReturn(false, StrUtil.nullToEmpty(message), 500);
    }

    public static PlatformReturn failure(int errorCode, String message) {
        return new PlatformReturn(false, StrUtil.nullToEmpty(message), errorCode);
    }

    public static PlatformReturn badRequest(String message) {
        return new PlatformReturn(false, StrUtil.nullToEmpty(message), 400);
    }

    public static PlatformReturn notFound(String message) {
        return new PlatformReturn(false, StrUtil.nullToEmpty(message), 404);
    }

    public static PlatformReturn illegalParameter() {
        return new PlatformReturn(false, "参数错误", 400);
    }

    public static PlatformReturn noPermission() {
        return badRequest("您没有此项操作的权限");
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }


    public Map<String, Object> getResults() {
        return this.results;
    }

    public void setResults(Map<String, Object> results) {
        this.results = results;
    }

    public <T extends Serializable> PlatformReturn setItems(Collection<T> items) {
        if (CollUtil.isEmpty(this.results)) {
            this.results = new HashMap(INITIAL_CAPACITY);
        }

        this.results.put("items", items);
        return this;
    }

    public PlatformReturn setItemsMap(Collection<Map<String, Object>> itemsMap) {
        if (CollUtil.isEmpty(this.results)) {
            this.results = new HashMap(INITIAL_CAPACITY);
        }

        this.results.put("items", itemsMap);
        return this;
    }

    public PlatformReturn setPageBean(int pageNo, int pageSize, int totalCount) {
        if (pageNo >= 0 && pageSize > 0 && totalCount >= 0) {
            PageBean pageBean = new PageBean();
            pageBean.setPageNo(pageNo);
            pageBean.setPageSize(pageSize);
            pageBean.setTotalCount(totalCount);
            return this.setPageBean(pageBean);
        } else {
            return this;
        }
    }

    public PlatformReturn setPageBean(PageBean pageBean) {
        if (pageBean != null) {
            this.addResultValue("pageBean", pageBean);
        }

        return this;
    }
    public PlatformReturn setPageBean(Page page) {
        if (page != null) {
            PageBean pageBean = new PageBean();
            pageBean.setPageNo(page.getNumber());
            pageBean.setPageSize(page.getSize());
            pageBean.setTotalCount((int) page.getTotalElements());
            return this.setPageBean(pageBean);
        }

        return this;
    }


    public <T extends Serializable> PlatformReturn setItem(T object) {
        if (CollUtil.isEmpty(this.results)) {
            this.results = new HashMap(INITIAL_CAPACITY);
        }

        this.results.put("item", object);
        return this;
    }

    public <T extends Serializable> PlatformReturn addResultValue(String name, T value) {
        if (StrUtil.isNotEmpty(name)) {
            if (this.results == null) {
                this.results = new LinkedHashMap();
            }

            this.results.put(name, value);
        }

        return this;
    }

    public <T extends Serializable> T getResultValue(String name) {
        return this.results != null && StrUtil.isNotEmpty(name) ? (T) this.results.get(name) : null;
    }


    public String getTraceCode() {
        return this.traceCode;
    }

    public void setTraceCode(String traceCode) {
        this.traceCode = traceCode;
    }



    @JsonIgnore
    public <T extends Serializable> T getItem() {
        return this.getResultValue("item");
    }

    @JsonIgnore
    public <T extends Serializable> T getItems() {
        return this.getResultValue("items");
    }

    public <T> Collection<T> getItems(Class<T> clazz) {
        Collection<Map<String, Object>> itemsMap = (Collection)this.getResultValue("items");
        if (!CollUtil.isNotEmpty(itemsMap)) {
            return Collections.emptyList();
        } else {
            Collection<T> items = new ArrayList(itemsMap.size());
            Iterator var4 = itemsMap.iterator();

            while(var4.hasNext()) {
                Map<String, Object> map = (Map)var4.next();
                ObjectMapper mapper = new ObjectMapper();
                items.add(mapper.convertValue(map,clazz));
            }

            return items;
        }
    }
    @Override
    public String toString() {
        ObjectMapper mapper=new ObjectMapper();

        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
