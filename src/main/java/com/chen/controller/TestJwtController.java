package com.chen.controller;

import com.chen.vo.ResultVO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chen
 * @crete 2022-08-30-22:43
 */
@RestController
@CrossOrigin
public class TestJwtController {

    @PostMapping("/test1")
    public ResultVO test1(){
        return ResultVO.ok("test1");
    }


    @PostMapping("/test2")
    public ResultVO test2(){
        return ResultVO.ok("test2");
    }
}
