package com.reflect.filter;

import com.reflect.controller.UserController;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Dispatcher implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Dispatcher 진입");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

//        System.out.println("ContextPath : " + req.getContextPath()); // 프로젝트 시작 주소
//        System.out.println("식별자 주소 : " + req.getRequestURI()); // 끝 주소
//        System.out.println("전체 주소 : " + req.getRequestURL()); // 전체 주소

        // /reflect/user -> /user로 파싱하기
        String endPoint = req.getRequestURI().replaceAll(req.getContextPath(), "");
        System.out.println("endPoint : " + endPoint);

        /*
         * 이와 같이 분기처리하면 확장성이 매우 떨어진다.
         * UserController에 새로운 메소드를 생성할 때마다 분기처리 로직을 추가해줘야하기 때문이다.
         *
         * -> reflection의 필요성이 여기서 나타난다
         *
         * reflection을 활용하면 해당 필터에서 UserController의 메소드를 분석하여 실행할 수 있다.
         * */
        UserController userController = new UserController();
        if (endPoint.equals("/join")) {
            userController.join();
        } else if (endPoint.equals("/login")) {
            userController.login();
        } else if (endPoint.equals("/user")) {
            userController.user();
        }


    }
}
