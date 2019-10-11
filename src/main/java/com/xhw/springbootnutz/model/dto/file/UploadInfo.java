package com.xhw.springbootnutz.model.dto.file;

import java.io.Serializable;


/**
* @Description:
* @Author:         xhw
* @CreateDate:     2019-10-11 10:56
*/
public class UploadInfo implements Serializable {

    private String uuid;
    private String name;
    private String content_type;
    private String url;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
