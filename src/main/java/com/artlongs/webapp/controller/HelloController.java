package com.artlongs.webapp.controller;/**
 * Created by Administrator on 14-4-6.
 */

import com.artlongs.webapp.service.WorldService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Function:
 * ========================================
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-4-6
 * ========================================
 */

@Controller
@RequestMapping("")
public class HelloController {

    @Resource
    private WorldService worldService;
    private static final Logger log = LoggerFactory.getLogger(HelloController.class);


    @RequestMapping({"","/index"})
    public String index(HttpServletRequest req ,Model model){

        model.addAttribute("message", "HELLO LITON!");
        model.addAttribute("worldList", worldService.makeSomeWorlds());

        return "/hello";
    }

}
