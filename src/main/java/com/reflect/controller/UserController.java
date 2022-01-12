package com.reflect.controller;

import com.reflect.anno.RequestMapping;

public class UserController {

    // "/join" 은 RequestMapping 어노테이션의 value() 메소드에 들어간다
    @RequestMapping("/join")
    public String join() {
        System.out.println("join() 함수 호출됨");
        return "/";
    }

    @RequestMapping("/login")
    public String login() {
        System.out.println("login() 함수 호출됨");
        return "/";
    }

    @RequestMapping("/user")
    public String user() {
        System.out.println("user() 함수 호출됨");
        return "/";
    }

    @RequestMapping("/hello")
    public String hello() {
        System.out.println("hello() 함수 호출됨");
        return "/";
    }
}
