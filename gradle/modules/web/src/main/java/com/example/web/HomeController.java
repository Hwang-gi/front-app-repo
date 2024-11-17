package com.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        // "home.jsp" 파일을 렌더링
        return "index";  // /WEB-INF/jsp/index.jsp
    }
}
