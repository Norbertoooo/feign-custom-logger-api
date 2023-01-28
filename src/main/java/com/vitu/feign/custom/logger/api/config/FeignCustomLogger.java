package com.vitu.feign.custom.logger.api.config;

import feign.Logger;
import feign.Request;
import feign.Response;
import feign.Util;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;

@Slf4j
public class FeignCustomLogger extends Logger {
    @Override
    protected void log(String s, String s1, Object... objects) {
    }

    @Override
    protected void logRequest(String configKey, Level logLevel, Request request) {

        log.info("-----> Request Begin");
        log.info("-----> Http method: {}", request.httpMethod());
        log.info("-----> Url: {}", request.url());
        log.info("-----> Headers: {}", request.headers());

        if (Objects.nonNull(request.body())) {
            log.info("-----> Request Body: {}", new String(request.body()));
        } else {
            log.info("-----> Request Body: ");
        }
        log.info("-----> Request End");

    }

    @Override
    protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response, long elapsedTime) throws IOException {

        byte[] bodyData = Util.toByteArray(response.body().asInputStream());
        String responseBody = Util.decodeOrDefault(bodyData, Util.UTF_8, "Binary data");

        log.info("<----- Response Begin");
        log.info("<----- Http method: {}", response.request().httpMethod());
        log.info("<----- Url: {}", response.request().url());
        log.info("<----- Headers: {}", response.headers());
        log.info("<----- Http Status: {}", response.status());
        log.info("<----- Response Body: {}", responseBody);
        log.info("<----- Response End");

        return response.toBuilder().body(bodyData).build();
    }
}
