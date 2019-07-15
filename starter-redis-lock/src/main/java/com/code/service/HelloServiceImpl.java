package com.code.service;


public class HelloServiceImpl implements HelloService{
    @Override
    public void sayHello(String msg) {
        System.out.println("my starter test !!! msg = " + msg);
    }
}
