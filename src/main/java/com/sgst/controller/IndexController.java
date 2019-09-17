package com.sgst.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
public class IndexController {

    @ApiIgnore
    @RequestMapping({"/",""})
    String index(){
        return "redirect:swagger-ui.html";
    }
}
