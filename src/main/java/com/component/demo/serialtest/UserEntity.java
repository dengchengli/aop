package com.component.demo.serialtest;

/**
 * @Author: Dely
 * @Date: 2020/1/8 12:54
 */
public class UserEntity implements Cloneable{
    private final static long serialVersionUID = 1;

    private int id;
    static String name;
    static int age = 10;

    public UserEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
