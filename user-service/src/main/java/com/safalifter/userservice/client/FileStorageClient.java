package com.safalifter.userservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "file-storage", path = "/v1/file-storage")
public interface FileStorageClient {
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<String> uploadImageToFIleSystem(@RequestPart("image") MultipartFile file);

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Void> deleteImageFromFileSystem(@PathVariable String id);
}