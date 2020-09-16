package com.code;

import com.code.data.hello.People;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;

public class ClassLoadDemo {

    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        People people = new People();
        ClassLoader classLoader = people.getClass().getClassLoader();
        System.out.println(classLoader);

        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> math = classLoader.loadClass("Test");
        Object o = math.newInstance();
        System.out.println(o.toString());


    }

}
