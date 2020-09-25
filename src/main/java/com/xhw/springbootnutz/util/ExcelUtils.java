package com.xhw.springbootnutz.util;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

/**
 * Created by Somuns on 2017/9/1
 */
public class ExcelUtils {



    /**
     * Excel 导入解析为 List
     *
     * @param file
     * @param params
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> importExcel(MultipartFile file, ImportParams params, Class<T> clazz) {
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            List<T> list = ExcelImportUtil.importExcel(inputStream, clazz, params);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
