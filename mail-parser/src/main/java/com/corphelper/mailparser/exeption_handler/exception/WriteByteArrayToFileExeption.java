package com.corphelper.mailparser.exeption_handler.exception;

import lombok.Data;

@Data
public class WriteByteArrayToFileExeption extends RuntimeException {

    public WriteByteArrayToFileExeption(String message) {
        super(message);
    }

}
