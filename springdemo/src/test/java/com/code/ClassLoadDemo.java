package com.code;

import com.code.data.hello.People;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ClassLoadDemo {

    public static void main(String[] args) {
        People people = new People();
        ClassLoader classLoader = people.getClass().getClassLoader();
        System.out.println(classLoader);


    }
}
