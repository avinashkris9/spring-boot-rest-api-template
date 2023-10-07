package com.github.avinashkris9.springboot.api.template.exception;

public class EntityNotFoundException extends  RuntimeException {

    public EntityNotFoundException(String errorCode) {
        super(errorCode);
    }
}
