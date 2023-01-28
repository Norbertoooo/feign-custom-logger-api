package com.vitu.feign.custom.logger.api.config;

import feign.Logger;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients
public class FeignClientConfig {

    @Bean
    public Logger feignLogger() {
        return new FeignCustomLogger();
    }
}
