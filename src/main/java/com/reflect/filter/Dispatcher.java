package com.reflect.filter;

import com.reflect.anno.RequestMapping;
import com.reflect.controller.UserController;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Dispatcher implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
//        System.out.println("Dispatcher 진입");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

//        System.out.println("ContextPath : " + req.getContextPath()); // 프로젝트 시작 주소
//        System.out.println("식별자 주소 : " + req.getRequestURI()); // 끝 주소
//        System.out.println("전체 주소 : " + req.getRequestURL()); // 전체 주소

        // /reflect/user -> /user로 파싱하기
        String endPoint = req.getRequestURI().replaceAll(req.getContextPath(), "");
        System.out.println("endPoint : " + endPoint);

        UserController userController = new UserController();
        /*
         * [아래와 같이 분기처리하면 확장성이 매우 떨어진다.]
         *
         * UserController에 새로운 메소드를 생성할 때마다 분기처리 로직을 추가해줘야하기 때문이다.
         *
         * -> reflection의 필요성이 여기서 나타난다
         *
         * reflection을 활용하면 해당 필터에서 UserController의 메소드를 분석하여 실행할 수 있다.
         *
        if (endPoint.equals("/join")) {
            userController.join();
        } else if (endPoint.equals("/login")) {
            userController.login();
        } else if (endPoint.equals("/user")) {
            userController.user();
        }
        * */

        /*
         * [리플렉션으로 -> 메소드를 런타임시점에 찾아 -> 실행한다]
         *
         * UserController에서 메소드를 추가해도 메소드 탐색 로직을 변경할 필요 없다.
         *
         * 하지만 endpoint와 메소드명이 동일해야만 가능한 방식이다.
         * */
        // getDeclaredMethods : 해당 클래스 파일의 메소드만 반환한다.
        Method[] methods = userController.getClass().getDeclaredMethods();
//        for (Method method : methods) {
////            System.out.println(method.getName());
//            // endpoint와 메소드의 이름이 동일하면 해당 메소드를 실행시킨다
//
//            if (endPoint.equals("/" + method.getName())) {
//                try {
//                    // invoke()를 통해 메소드를 실행시킬 수 있다
//                    method.invoke(userController);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }

        /*
         * [메소드 명이 아닌 어노테이션을 활용한 리플렉션]
         * */
        for (Method method : methods) {
            // RequestMapping 어노테이션이 붙은 메소드를 찾아야 한다
            Annotation annotation = method.getDeclaredAnnotation(RequestMapping.class);


            // annotation을 RequestMapping으로 다운캐스팅 해야 한다.
            RequestMapping requestMapping = (RequestMapping) annotation;

            // RequestMapping 어노테이션에 들어간 String 값을 확인한다. -> 매핑할 endPoint 값이다다
            System.out.println(requestMapping.value());

            // endpoint와 같은 value를 갖는 어노테이션을 찾으면 메소드 실행 후 반복문 탈출출
            if (requestMapping.value().equals(endPoint)) {
                try {

                    // UserController의 메소드를 실행시킨 후, return 값을 path변수에 담는다.
                    String path = (String) method.invoke(userController);

                    // RequestDispatcher을 통해 path로 forward하면 필터를 거치지 않는다 (내부에서 이루어지기 때문)
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
                    requestDispatcher.forward(req, res);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }


    }
}
