package com.example.xiaoxiong.test;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.nio.file.FileAlreadyExistsException;

public class TestClass {

    @BindAddress("http://www.google.com.cn")
    private String address;
    @BindPort("8888")
    private String port;
    private int number;

    public void printInfo() {
        System.out.println("wangyuchao : info is " + address + "：" + port);
    }

    private void myMethod(int number, String sex) {

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @BindGet("mike")
    void getHttp(String param) {
        String url = "http://www.baidu.com/?username" + param;
        System.err.println("get------>" + url);
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD})
    @Documented
    @Inherited
    public @interface Bind {
        int value() default 1;

        boolean canBeNull() default false;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface BindPort {
        String value() default "8080";
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface BindAddress {
        String value() default "127.0.0.0";
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface BindGet {
        String value() default "";
    }
}
