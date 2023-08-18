package com.safalifter.authservice.exc;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
public class ValidationException extends RuntimeException {
    private Map<String, String> validationErrors;
}