package com.xhw.springbootnutz.controller;

import com.xhw.springbootnutz.model.dto.ajax.AjaxCode;
import com.xhw.springbootnutz.model.dto.ajax.AjaxResult;
import com.xhw.springbootnutz.model.dto.file.UploadInfo;
import com.xhw.springbootnutz.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
* @Description:    文件上传API
* @Author:         xhw
* @CreateDate:     2019-10-11 10:56
*/
@RestController
@RequestMapping("/api/upload")
public class FileUploadRest {

    @Autowired
    private FileUtils fileUpload;

    /**
     * 单文件上传
     *
     * @param file
     * @return
     */
    @PostMapping("/file")
    @CrossOrigin
    public AjaxResult uploadFile(@RequestParam("file") MultipartFile file) {
        if (null == file) {
            return new AjaxResult(AjaxCode.CONTENT_ERROR, "文件不存在!");
        } else {
            UploadInfo info = fileUpload.uploadFile(file);
            if (null == info) {
                return new AjaxResult(AjaxCode.FAIL, "文件上传失败!");
            } else {
                return new AjaxResult(AjaxCode.SUCCESS, "上传成功!", info);
            }
        }
    }

    /**
     * 多文件上传
     *
     * @param files
     * @return
     */
    @PostMapping("/files")
    @CrossOrigin
    public AjaxResult uploadFileList(@RequestParam("files") MultipartFile[] files) {
        if (null == files || files.length < 1) {
            return new AjaxResult(AjaxCode.CONTENT_ERROR, "文件不存在!");
        } else {
            List<UploadInfo> infoList = fileUpload.uploadFileList(files);
            if (null == infoList || infoList.size() < 1) {
                return new AjaxResult(AjaxCode.FAIL, "文件上传失败!");
            } else {
                return new AjaxResult(AjaxCode.SUCCESS, "上传成功!", infoList);
            }
        }
    }
}
