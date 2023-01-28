package com.vitu.feign.custom.logger.api.handler;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ControllerHandler {

    @ExceptionHandler(FeignException.class)
    public ResponseEntity feignClientExceptionHandler(FeignException feignException) {
        log.error("Error on client - ", feignException);
        return ResponseEntity.status(feignException.status()).body(feignException.getMessage());
    }
}
