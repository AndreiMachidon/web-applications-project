package com.andrei.project_web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @RequestMapping({"", "/", "/home"})
    public ModelAndView getHome() {
        return new ModelAndView("main");
    }

    @GetMapping("/login")
    public String showLogInForm() {
        return "login";
    }

    @GetMapping("/access_denided")
    public String accessDeniedPage() {
        return "accessDenied";
    }
}
