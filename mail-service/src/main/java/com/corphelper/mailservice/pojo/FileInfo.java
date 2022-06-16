package com.corphelper.mailservice.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo {

    private String fileName;

    private String extension;

    private byte[] fileBytes;

}
