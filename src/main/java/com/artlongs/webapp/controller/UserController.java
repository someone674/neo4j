package com.artlongs.webapp.controller;

import com.artlongs.base.controller.BaseController;
import com.artlongs.webapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/user")
public class UserController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;



    @RequestMapping("/list")
    public String index(HttpServletRequest req ,Model model){

        model.addAttribute("message", "HELLO LITON!");
        model.addAttribute("users", userService.makeSomeUsers());
        model.addAttribute("show", "I'm is linton.");

        return "/user";
    }

}
