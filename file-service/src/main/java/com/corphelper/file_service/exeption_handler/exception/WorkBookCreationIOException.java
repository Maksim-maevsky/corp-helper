package com.corphelper.file_service.exeption_handler.exception;

import lombok.Data;

@Data
public class WorkBookCreationIOException extends RuntimeException {

    public WorkBookCreationIOException(String message) {
        super(message);
    }

}
