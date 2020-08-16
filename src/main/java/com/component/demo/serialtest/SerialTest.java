package com.component.demo.serialtest;

/**
 * @Author: Dely
 * @Date: 2020/1/8 13:02
 */
public class SerialTest {
    public static void main(String[] args) throws CloneNotSupportedException{

        UserEntity userEntity = new UserEntity();

        userEntity.setId(2);

        UserEntity.age = 66;

        UserEntity us = (UserEntity) userEntity.clone();

        System.out.println(UserEntity.age);
    }
}
