package com.artlongs.base.controller;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class BaseController {

    /**
     * SPRING MVC 让人很无奈的类型初始化注册
     * 这个方法本应当应用于扩展类别的注册的，结束基本的类型也要注册一下，很无语:(
     * @param binder
     */
    @InitBinder
    private void dateBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),true));
    }

    /**
     * 对BindingResult 进行基本的封装以方便检查前端输入的格式的错误信息
     * @param result
     * @return
     */
    protected List<String> getErrorMessageListFromBindingResult(BindingResult result) {
        List<String> errorMsgList = new ArrayList<String>();
        for(ObjectError error : result.getAllErrors()) {
            errorMsgList.add(error.getDefaultMessage());
        }
        return errorMsgList;
    }

}
