package com.dionysos.api.diary.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class S3Uploader implements FileUploader {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    public String upload(MultipartFile uploadFile, String dirName) {
        String fileName = toFileName(dirName, uploadFile);

        return putS3(uploadFile, fileName);
    }

    private String toFileName(String dirName, MultipartFile uploadFile) {
        StringBuilder sb = new StringBuilder();
        sb.append(dirName);
        sb.append("/");
        sb.append(System.currentTimeMillis());
        sb.append("_");
        sb.append(uploadFile.getOriginalFilename());

        return sb.toString();
    }

    private String putS3(MultipartFile uploadFile, String fileName) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(uploadFile.getContentType());

        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, fileName, uploadFile.getInputStream(),
                    metadata).withCannedAcl(CannedAccessControlList.PublicRead);
            amazonS3Client.putObject(putObjectRequest);
        } catch (IOException e) {
            log.error("{} upload fail...", fileName);
            // TODO: 적절한 예외 생성
            throw new RuntimeException("파일 업로드 실패!");
        }

        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

}
