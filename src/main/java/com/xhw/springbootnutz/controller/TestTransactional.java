package com.xhw.springbootnutz.controller;

import com.xhw.springbootnutz.model.dto.ajax.AjaxResult;
import com.xhw.springbootnutz.service.TestTransactionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("test")
public class TestTransactional {

    @Autowired
    TestTransactionalService testTransactionalService;
    @GetMapping
    public AjaxResult test() {
        testTransactionalService.testA();

        return new AjaxResult(200,"","");
    }
    @GetMapping("/testC")
    public AjaxResult testc() {
        testTransactionalService.testC();
        return new AjaxResult(200,"","");
    }
}
