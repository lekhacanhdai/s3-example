package com.example.s3.controller;

import com.example.s3.utils.S3Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author lkadai0801
 * @since 17/12/2022
 */

@RestController
@RequiredArgsConstructor
public class S3Controller {
    private final S3Utils s3Utils;

    @GetMapping("/keys")
    public List<String> getAllKey(){
        return s3Utils.getAllKey();
    }

    @PostMapping("/objects")
    public Map<String, Object> uploadObjectToS3(@RequestParam("file") MultipartFile multipartFile){
        try {
            s3Utils.uploadObjectToS3(multipartFile.getOriginalFilename(), multipartFile.getInputStream(), multipartFile.getContentType());
        }
        catch (IOException e){
            return Map.of("success", false, "message", "Upload error", "key", e.getCause());
        }
        return Map.of("success", true, "message", "Upload successfully!");
    }
}
