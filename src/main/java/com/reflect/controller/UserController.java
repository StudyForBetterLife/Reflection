package com.reflect.controller;

import com.reflect.anno.RequestMapping;
import com.reflect.controller.dto.JoinDto;
import com.reflect.controller.dto.LoginDto;
import com.reflect.model.User;

public class UserController {

    // "/join" 은 RequestMapping 어노테이션의 value() 메소드에 들어간다
    @RequestMapping("/user/join")
    public String join(JoinDto dto) { // username, password, email
        System.out.println("join() 함수 호출됨");
        System.out.println(dto);
        return "/";
    }

    @RequestMapping("/user/login")
    public String login(LoginDto dto) { // username, password
        System.out.println("login() 함수 호출됨");
        System.out.println(dto);
        return "/";
    }

    @RequestMapping("/list")
    public String list(User user) { // id, username, password, email
        System.out.println("list() 함수 호출됨");
        System.out.println(user);
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
