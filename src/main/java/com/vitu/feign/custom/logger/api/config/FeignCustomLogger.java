package com.vitu.feign.custom.logger.api.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import feign.Logger;
import feign.Request;
import feign.Response;
import feign.Util;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Objects;

@Slf4j
public class FeignCustomLogger extends Logger {

    @Override
    protected void log(String s, String s1, Object... objects) {
    }

    @Override
    protected void logRequest(String configKey, Level logLevel, Request request) {

        log.info("--------------- Request ----------------");
        log.info("-----> Http method: {}", request.httpMethod());
        log.info("-----> Url: {}", request.url());
        log.info("-----> Headers: {}", request.headers());

        if (Objects.nonNull(request.body())) {
            String formattedBody = this.getFormattedBody(request.body());
            log.info("-----> Request Body: \n{}", formattedBody);
        } else {
            log.info("-----> Request Body: ");
        }

    }

    @Override
    protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response, long elapsedTime) throws IOException {

        byte[] bodyData = Util.toByteArray(response.body().asInputStream());
        log.info("--------------- Response ---------------");
        log.info("<----- Url: {}", response.request().url());
        log.info("<----- Headers: {}", response.headers());
        log.info("<----- Http Status: {}", response.status());

        if (Objects.nonNull(bodyData) && bodyData.length != 0) {
            String formattedBody = this.getFormattedBody(bodyData);
            log.info("<----- Response Body: \n{}", formattedBody);
        } else {
            log.info("<----- Response Body: ");

        }

        return response.toBuilder().body(bodyData).build();
    }

    private String getFormattedBody(byte[] body) {
        Gson gson = new GsonBuilder().setPrettyPrinting().setLenient().create();
        String responseBody = Util.decodeOrDefault(body, Util.UTF_8, "Binary data");
        return gson.toJson(JsonParser.parseString(responseBody));
    }
}
