package com.code.annotation.enable;

import org.springframework.boot.autoconfigure.AutoConfigurationImportSelector;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author ccy
 * @description
 * @time 2018/10/19 下午4:53
 */
public class MyImportSelect implements ImportSelector {
    /**
     * 这个类返回的类名会被注册到ioc
     * @param annotationMetadata
     * @return 类名
     */
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{"com.code.annotation.enable.EnableBeanTest"};
    }
}
