package com.reflect.filter;

import org.junit.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class SetNamingTest {

    @Test
    public void 키값을Setter로바꾸기() {
        String key = "username";
        String firstKey = "set";
        String upperKey = key.substring(0, 1).toUpperCase();
        String remainKey = key.substring(1);

        System.out.println(firstKey);
        System.out.println(upperKey);
        System.out.println(remainKey);

        String result = firstKey + upperKey + remainKey;
        System.out.println(result);
    }
}