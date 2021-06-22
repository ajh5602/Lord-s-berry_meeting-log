package com.mdm.server.exception;

/**
 * name : CertFailedException
 * usage : 인증 실패 Exception 직접 구현
 * @author ash
 * @date 2021-06-17
**/
public class CertFailedException extends RuntimeException{

    public CertFailedException() {
        super();
    }

    public CertFailedException(String message) {
        super(message);
    }

    public CertFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CertFailedException(Throwable cause) {
        super(cause);
    }
}
