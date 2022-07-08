package com.corphelper.file_service.exeption_handler.exception;

import lombok.Data;

@Data
public class WrongPartStorageNameException extends RuntimeException {

    public WrongPartStorageNameException(String message) {
        super(message);
    }

}
