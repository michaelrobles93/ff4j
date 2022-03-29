package com.construo.ff4j.config;

import lombok.extern.log4j.Log4j2;
import org.ff4j.FF4j;
import org.ff4j.audit.repository.JdbcEventRepository;
import org.ff4j.cache.FF4JCacheManager;
import org.ff4j.cache.FF4jCacheManagerRedis;
import org.ff4j.property.store.JdbcPropertyStore;
import org.ff4j.store.JdbcFeatureStore;
import org.ff4j.web.FF4jProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ComponentScan("org.ff4j.aop")
@Log4j2
public class FF4jConfiguration implements FF4jProvider {

    private final DataSource dataSource;
    private final FF4JCacheManager cacheManager;

    @Autowired
    public FF4jConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
        this.cacheManager = new FF4jCacheManagerRedis();
    }

    @Bean
    @Override
    public FF4j getFF4j() {
        FF4j ff4j = new FF4j();

        //ff4j.setFeatureStore(new InMemoryFeatureStore());
        ff4j.setFeatureStore(new JdbcFeatureStore(dataSource));
        ff4j.setPropertiesStore(new JdbcPropertyStore(dataSource));
        ff4j.setEventRepository(new JdbcEventRepository(dataSource));
        ff4j.disableAlterBeanThrowInvocationTargetException();

        // Enabling audit and monitoring
        ff4j.audit(true);

        // When evaluting not existing features, ff4j will create then but disabled
        ff4j.autoCreate(true);

        // To define RBAC access, the application must have a logged user
        //ff4j.setAuthManager(...);

        // To define a cacher layer to relax the DB, multiple implementations
        ff4j.cache(cacheManager);

        return ff4j;
    }
}
