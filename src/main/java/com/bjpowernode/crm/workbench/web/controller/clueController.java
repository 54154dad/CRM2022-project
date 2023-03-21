package com.bjpowernode.crm.workbench.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Classname clueController
 * @Date 2023/2/26
 * @Created by YQ
 */
@Controller
public class clueController {
    @RequestMapping("/workbench/clue/index.do")
    public String index(){
        return "/workbench/clue/index";
    }
}
