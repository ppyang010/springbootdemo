package com.code.example.httpclient;

import org.springframework.lang.Nullable;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * byte[]  MultipartFile
 * 参考:https://github.com/spring-cloud/spring-cloud-netflix/issues/2246
 *
 * @author ccy
 */
public class InMemoryMultipartFile implements MultipartFile {

    private String originalFileName;
    private String contentType;
    private byte[] payload;
    private String name;

    public InMemoryMultipartFile(File file) throws IOException {
        this.originalFileName = file.getName();
        this.payload = FileCopyUtils.copyToByteArray(file);
        this.name = "file";
        this.contentType = "application/octet-stream";
    }

    public InMemoryMultipartFile(String originalFileName, byte[] payload) {
        this.originalFileName = originalFileName;
        this.payload = payload;
        this.name = "file";
        this.contentType = "application/octet-stream";
    }

    public InMemoryMultipartFile(String name, String originalFileName, String contentType, byte[] payload) {
        if (payload == null) {
            throw new IllegalArgumentException("Payload cannot be null.");
        }
        this.name = name;
        this.originalFileName = originalFileName;
        this.contentType = contentType;
        this.payload = payload;
    }


    @Override
    public String getName() {
        return name;
    }

    @Nullable
    @Override
    public String getOriginalFilename() {
        return originalFileName;
    }

    @Nullable
    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public long getSize() {
        return payload.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return payload;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(payload);
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        new FileOutputStream(dest).write(payload);
    }
}
