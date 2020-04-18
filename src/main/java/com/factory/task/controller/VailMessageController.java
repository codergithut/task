package com.factory.task.controller;

import org.springframework.web.bind.annotation.GetMapping;


/**
 * Created by tianjian on 2020/4/18.
 */
public class VailMessageController {
    @GetMapping("/message")
    public String valilMessage() {

        return "haha";
    }
}
