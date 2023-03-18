package com.skypro.recipes.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class InfoController {

    @GetMapping
    @RequestMapping
   public String helloWorld() {
        return "запущено";
    }

    @GetMapping("/path/to/info")
    public String info(@RequestParam String info) {
        return info + "Информация о проекте";
    }

}
