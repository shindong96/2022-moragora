package com.woowacourse.moragora.exception;

import org.springframework.http.HttpStatus;

public class NoParameterException extends ClientRuntimeException {

    private static final String MESSAGE = "값이 입력되지 않았습니다.";

    public NoParameterException() {
        super(MESSAGE, HttpStatus.BAD_REQUEST);
    }
}
