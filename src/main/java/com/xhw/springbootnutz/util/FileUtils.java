package com.xhw.springbootnutz.util;


import com.google.api.client.util.Lists;
import com.xhw.springbootnutz.model.dto.file.UploadInfo;
import com.xhw.springbootnutz.model.param.MinioProperties;
import io.minio.MinioClient;
import io.minio.errors.MinioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

/**
 *
 */
@Component
public class FileUtils {

    @Autowired
    private MinioProperties minioProperties;

    /**
     * 多文件上传
     *
     * @param files
     * @return
     */
    public List<UploadInfo> uploadFileList(MultipartFile[] files) {
        List<UploadInfo> uploadInfos = Lists.newArrayList();
        try {
            MinioClient minioClient = new MinioClient(minioProperties.getEndpoint(), minioProperties.getAccessKey(), minioProperties.getSecretKey());
            if (files != null && files.length != 0) {
                for (MultipartFile f : files) {
                    UploadInfo uploadInfo = new UploadInfo();
                    uploadInfo.setContent_type(f.getContentType());
                    uploadInfo.setName(f.getOriginalFilename());
                    uploadInfo.setUuid(UUID.randomUUID().toString().replaceAll("-", "") + this.getPrefix(f.getOriginalFilename()));
                    uploadInfo.setUrl(minioProperties.getBackurl() + "/" + minioProperties.getBucket() + "/" + uploadInfo.getUuid());
                    minioClient.putObject(minioProperties.getBucket(), uploadInfo.getUuid(), f.getInputStream(), f.getContentType());
                    uploadInfos.add(uploadInfo);
                }
            }
        } catch (MinioException e) {
            System.out.println("Error" + e);
        } catch (IOException ioe) {
            System.out.println("Error" + ioe);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return uploadInfos;
    }


    /**
     * 单文件上传
     *
     * @param file
     * @return
     */
    public UploadInfo uploadFile(MultipartFile file) {
        UploadInfo uploadInfo = new UploadInfo();
        try {
            MinioClient minioClient = new MinioClient(minioProperties.getEndpoint(), minioProperties.getAccessKey(), minioProperties.getSecretKey());
            uploadInfo.setContent_type(file.getContentType());
            uploadInfo.setName(file.getOriginalFilename());
            uploadInfo.setUuid(UUID.randomUUID().toString().replaceAll("-", "") + this.getPrefix(file.getOriginalFilename()));
            uploadInfo.setUrl(minioProperties.getBackurl() + "/" + minioProperties.getBucket() + "/" + uploadInfo.getUuid());
            minioClient.putObject(minioProperties.getBucket(), uploadInfo.getUuid(), file.getInputStream(), file.getContentType());
        } catch (MinioException e) {
            System.out.println("Error" + e);
        } catch (IOException ioe) {
            System.out.println("Error" + ioe);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return uploadInfo;
    }

    public static String getPrefix(String url) {
        return url.substring(url.lastIndexOf("."));
    }

    /**
     * 要移除的对象名称
     *
     * @param objName
     */
    public void removeObject(String objName) {
        try {
            MinioClient minioClient = new MinioClient(minioProperties.getEndpoint(), minioProperties.getAccessKey(), minioProperties.getSecretKey());
            minioClient.removeObject(minioProperties.getBucket(), objName);
        } catch (MinioException e) {
            System.out.println("Error: " + e);
        } catch (InvalidKeyException e) {
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }
}
