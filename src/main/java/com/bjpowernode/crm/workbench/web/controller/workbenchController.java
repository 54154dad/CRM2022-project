package com.bjpowernode.crm.workbench.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Classname workbenchController
 * @Date 2022/12/21
 * @Created by YQ
 */
@Controller
public class workbenchController {
    @RequestMapping("workbench/index.do")
    public String login(){
        return "workbench/index";
    }
}
