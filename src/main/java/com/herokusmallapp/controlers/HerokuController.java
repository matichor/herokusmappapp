package com.herokusmallapp.controlers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HerokuController {

    @GetMapping
    public String go(){
        return "dupa";
    }


}
