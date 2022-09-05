package com.chen.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Amin
 * @version 1.0
 * @date 2022/9/5/005 14:15
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/test1")
    public String test1() {
        return "test1";
    }


    @GetMapping("/test2")
    public String test2() {
        return "test2";
    }

}
