package com.openblog.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SearchController {

    @GetMapping("/search")
    private String search(HttpServletRequest request, Model model) {
        String keyword = (String) request.getParameter("keyword");
        model.addAttribute("keyword", keyword);
        return "Home/search";
    }

}
