package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/go/login")
    public String goToAbout(@RequestParam(value = "error", defaultValue = "false") Boolean b) {
        System.out.println(b);
        return "/auth/login";
    }

    @GetMapping("/perform_logout")
    public String logOut() {
        return "/auth/login";
    }

}
