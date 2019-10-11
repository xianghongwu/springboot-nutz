package com.xhw.springbootnutz.model.param;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author:         xhw
 * @CreateDate:     2019-10-11 9:53
 */
@ConfigurationProperties(prefix = "minio")
@Component
public class MinioProperties {

    /**
     * 对象存储服务器地址
     */
    private String endpoint;

    /**
     * 桶
     */
    private String bucket;

    /**
     * 账户名称
     */
    private String accessKey;

    /**
     * 账户密码
     */
    private String secretKey;

    /**
     * 访问地址
     */
    private String backurl;

    public String getBackurl() {
        return backurl;
    }

    public void setBackurl(String backurl) {
        this.backurl = backurl;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
