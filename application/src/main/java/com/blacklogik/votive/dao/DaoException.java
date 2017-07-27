package com.blacklogik.votive.dao;

public class DaoException extends RuntimeException {
    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
