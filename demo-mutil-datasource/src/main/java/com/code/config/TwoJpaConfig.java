package com.code.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

/**
 * @author ccy
 */

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="entityManagerFactoryTwo",
        transactionManagerRef="transactionManagerTwo",
        basePackages= { "com.code.dao.two" }) //设置Repository所在位置
public class TwoJpaConfig {


    @Autowired @Qualifier("twoDataSource")
    private DataSource twoDataSource;

    @Primary
    @Bean(name = "transactionManagerTwo")
    public PlatformTransactionManager transactionManagerTwo(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryTwo(builder).getObject());
    }

    @Primary
    @Bean(name = "entityManagerTwo")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryTwo(builder).getObject().createEntityManager();
    }



    @Primary
    @Bean(name = "entityManagerFactoryTwo")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryTwo (EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(twoDataSource)
                .properties(getVendorProperties())
                //设置实体类所在位置
                .packages("com.code.domain.two")
                .persistenceUnit("twoPersistenceUnit")
                .build();
    }

    @Autowired
    private JpaProperties jpaProperties;

    private Map<String, String> getVendorProperties() {
        return jpaProperties.getProperties();
    }



}
