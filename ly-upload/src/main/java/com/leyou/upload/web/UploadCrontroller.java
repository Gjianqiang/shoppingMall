package com.leyou.upload.web;

import com.leyou.upload.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("upload")
@Slf4j
public class UploadCrontroller {
    @Autowired
    private UploadService uploadService;
    @PostMapping("image")
    public ResponseEntity<String> uploadImage(@RequestParam("file")MultipartFile file){
        log.info("进入到uploadImage");
        String url=uploadService.uploadImage(file);


        return ResponseEntity.ok(url);
    }
    @PostMapping("test")
    public ResponseEntity<String> test(){
        log.info("进入到uploadImage ,testtesttesttesttest");
        String url="进入到uploadImage";


        return ResponseEntity.ok(url);
    }

    public static void main(String[] args) {

    }
}
