package com.reflect.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * 어노테이션을 달아줄 타입을 결정할 수 있다 (클래스, 변수, 메소드)
 * -> @Target 어노테이션을 통해 해당 어노테이션이 동작할 위치를 결정할 수 있다
 *
 *
 * 어노테이션의 동작 시점을 결정할 수 있다. (컴파일 시점 or 런타임 시점)
 * -> 사용자 요청시 즉, 런타임시에 동작하도록 결정해야한다.
 * -> @Retention 어노테이션을 통해 동작 시점을 정할 수 있다
 * */
@Target({ElementType.METHOD}) // 메소드에 사용할 어노테이션
@Retention(RetentionPolicy.RUNTIME) // 런타임시 동작할 어노테이션
public @interface RequestMapping {

    String value();
}
