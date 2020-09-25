package com.xhw.springbootnutz.controller;

import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.xhw.springbootnutz.model.dto.StudentInfoDto;
import com.xhw.springbootnutz.model.dto.ajax.AjaxResult;
import com.xhw.springbootnutz.model.dto.ajax.AjaxResultUtils;
import com.xhw.springbootnutz.model.dto.ajax.ResultState;
import com.xhw.springbootnutz.model.mapped.StudentInfoDtoTemp;
import com.xhw.springbootnutz.service.TestTransactionalService;
import com.xhw.springbootnutz.util.ExcelUtils;
import com.xhw.springbootnutz.util.QRCodeUtils;
import com.xhw.springbootnutz.util.StringUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("import")
public class ImportStudentQR {

    @Autowired
    Dao dao;

    /**
     * 成绩批量导入
     *
     * @param file
     * @return
     */
    @PostMapping("/student")
    public AjaxResult multiImportScore (MultipartFile file) throws Exception {
        // 解析文件内容
        String originalFilename = file.getOriginalFilename();
        String fileName = originalFilename.substring(0, originalFilename.lastIndexOf("."));
        ImportParams params = new ImportParams();
        params.setTitleRows(0);
        params.setNeedVerify(true);
        List<StudentInfoDtoTemp> importList = ExcelUtils.importExcel(file, params, StudentInfoDtoTemp.class);
        int i=0;
        if(importList!=null && importList.size()>0){
            for (StudentInfoDtoTemp studentInfoDtoTemp : importList) {
                if(studentInfoDtoTemp.getStudentId()==null || studentInfoDtoTemp.getStuName()==null || studentInfoDtoTemp.getUnionId()==null){
                    return new AjaxResult(ResultState.INNER_ERROR, "数据错误");
                }
                String unionId=studentInfoDtoTemp.getUnionId();
                String salt="d380cce937f043bdb5c72f0ca072c773";
                String unionId_salt= StringUtils.encrypByMd5(unionId+"_"+salt);
                String url="https://m.gzeducard.net/userinfo?type=1&unionId="+unionId+"&md5="+unionId_salt;
                String stuName=studentInfoDtoTemp.getStudentId()+"_"+studentInfoDtoTemp.getStuName();
                QRCodeUtils.encode(url, null, "C:\\Users\\Shinelon\\Pictures\\student\\"+fileName, true,stuName);
                i++;
            }
        }else{
            return new AjaxResult(ResultState.INNER_ERROR, "文件数据为空");
        }
        return new AjaxResult(ResultState.OK, ResultState.ADD_SUCCESS,"一共生成："+i+"张二维码；");
    }
}
