package com.safalifter.authservice.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safalifter.authservice.exc.GenericErrorResponse;
import com.safalifter.authservice.exc.ValidationException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
public class CustomErrorDecoder implements ErrorDecoder {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Exception decode(String methodKey, Response response) {
        try (InputStream body = response.body().asInputStream()) {
            Map<String, String> errors =
                    mapper.readValue(IOUtils.toString(body, StandardCharsets.UTF_8), Map.class);
            if (response.status() == 400) {
                return ValidationException.builder()
                        .validationErrors(errors).build();
            } else
                return GenericErrorResponse
                        .builder()
                        .httpStatus(HttpStatus.valueOf(response.status()))
                        .message(errors.get("error"))
                        .build();

        } catch (IOException exception) {
            throw GenericErrorResponse.builder()
                    .httpStatus(HttpStatus.valueOf(response.status()))
                    .message(exception.getMessage())
                    .build();
        }
    }
}