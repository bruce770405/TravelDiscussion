package javaserver.controller.dto;

import lombok.Data;

@Data
public class FileUploadDto {
    private String fileName;
    private String body;
}
