package com.safalifter.authservice.client;

import com.safalifter.authservice.exc.GenericErrorResponse;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.body() == null) // 401-403
            throw GenericErrorResponse.builder()
                    .httpStatus(HttpStatus.valueOf(response.status()))
                    .message("No response body")
                    .build();

        try (InputStream body = response.body().asInputStream()) {
            String message = IOUtils.toString(body, StandardCharsets.UTF_8);
            return GenericErrorResponse
                    .builder()
                    .httpStatus(HttpStatus.valueOf(response.status()))
                    .message(message.substring(10, message.length() - 2))
                    .build();

        } catch (IOException exception) {
            throw GenericErrorResponse.builder()
                    .httpStatus(HttpStatus.valueOf(response.status()))
                    .message(exception.getMessage())
                    .build();
        }
    }
}