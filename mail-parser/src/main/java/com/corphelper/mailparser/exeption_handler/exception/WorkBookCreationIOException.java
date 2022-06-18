package com.corphelper.mailparser.exeption_handler.exception;

import lombok.Data;

@Data
public class WorkBookCreationIOException extends RuntimeException {

    public WorkBookCreationIOException(String message) {
        super(message);
    }

}
