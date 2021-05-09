package com.code;

import com.code.data.hello.People;

import java.io.FileNotFoundException;

public class ClassLoadDemo {

    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        People people = new People();
        ClassLoader classLoader = people.getClass().getClassLoader();
        System.out.println(classLoader);

        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> aClass = classLoader.loadClass("com.code.syn.Test");
        //类名随便写 主要是为了进入findClass逻辑中
        Class<?> math = myClassLoader.loadClass("com.code.MyMath");
        Object o = math.newInstance();
        System.out.println(o.toString());
    }

}
