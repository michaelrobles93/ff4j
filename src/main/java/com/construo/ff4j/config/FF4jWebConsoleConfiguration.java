package com.construo.ff4j.config;

import com.construo.ff4j.enums.FF4JIdentifier;
import lombok.extern.log4j.Log4j2;
import org.ff4j.FF4j;
import org.ff4j.core.Feature;
import org.ff4j.web.FF4jDispatcherServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisHash;

import java.util.Arrays;

@Configuration
// Setup FF4j first, not is required
@AutoConfigureAfter(FF4jConfiguration.class)
@Log4j2
@RedisHash(timeToLive = 100)
public class FF4jWebConsoleConfiguration extends SpringBootServletInitializer {

    @Value("${ff4j.webconsole.url}")
    private String url;

    @Bean
    @ConditionalOnMissingBean
    public FF4jDispatcherServlet getFF4jDispatcherServlet(FF4j ff4j) {
        FF4jDispatcherServlet ff4jConsoleServlet = new FF4jDispatcherServlet();
        ff4jConsoleServlet.setFf4j(ff4j);
        createFeature(ff4j);
        return ff4jConsoleServlet;
    }

    @Bean
    @SuppressWarnings({"rawtypes", "unchecked"})
    public ServletRegistrationBean registerFF4jServlet(FF4jDispatcherServlet ff4jDispatcherServlet) {
        log.info("Exposing FF4j web console on '{}' as property 'ff4j.webconsole.enable' is true", url);
        return new ServletRegistrationBean(ff4jDispatcherServlet, url + "/*");
    }

    private void createFeature(FF4j ff4j) {
        Arrays.stream(FF4JIdentifier.values()).forEach(identifier -> {
            String name = identifier.getName();
            boolean enable = identifier.isEnable();
            if (ff4j.exist(name)) {
                log.info("Feature {} already exists", name);
            } else {
                ff4j.createFeature(new Feature(name, enable));
                log.info("Feature {} created, status {}", name, enable ? "enable" : "disable");
            }
        });
    }
}
