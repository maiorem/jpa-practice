package com.maiorem.jpashop.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {

//    Logger log = LoggerFactory.getLogger(getClass()); => lombok으로 대체

    @RequestMapping("/")
    public String home() {
        log.info("home controller");
        return "home";
    }

}
