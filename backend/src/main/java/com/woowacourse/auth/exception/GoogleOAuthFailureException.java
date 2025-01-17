package com.woowacourse.auth.exception;

import com.woowacourse.moragora.exception.ClientRuntimeException;
import org.springframework.http.HttpStatus;

public class GoogleOAuthFailureException extends ClientRuntimeException {

    public GoogleOAuthFailureException(final String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
