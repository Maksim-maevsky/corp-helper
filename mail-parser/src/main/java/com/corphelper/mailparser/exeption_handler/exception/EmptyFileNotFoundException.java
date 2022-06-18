package com.corphelper.mailparser.exeption_handler.exception;

import lombok.Data;

@Data
public class EmptyFileNotFoundException extends RuntimeException {

    public EmptyFileNotFoundException(String message) {
        super(message);
    }

}
