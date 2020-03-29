package com.openblog.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping(value = {"/","/index","/article"})
    public String index(Model model) {
        
        return "Home/index";
    }

}
