package com.dog.service.cache.exception;

public class CacheException extends RuntimeException {

    private static final long serialVersionUID = 6046442858466224433L;

    public CacheException(String message) {
        super(message);
    }

    public CacheException(Throwable e) {
        super(e);
    }

    public CacheException(String message, Throwable cause) {
        super(message, cause);
    }
}