package com.example.redisdemo.Dao;

import java.io.Serializable;

public class User implements Serializable {
    private String sno;
    private String sname;
    private Integer age;

    public User() {
    }

    public User(String sno, String sname, Integer age) {
        this.sno = sno;
        this.sname = sname;
        this.age = age;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
