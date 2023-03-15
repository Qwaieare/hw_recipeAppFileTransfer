package com.skypro.recipes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class InfoController {

    @GetMapping
   public String hello() {
        return "запущено";
    }

    @GetMapping("/info")
    public String info() {
        return "Информация о проекте";
    }

}
