package com.jdbc2.CRUD.bean;

import java.sql.Date;

public class Customer {
    private int id;
    private String name;
    private String email;
    private Date birth;
    private Integer balance;

    public Customer() {
    }

    public Customer(int id, String name, String email, Date birth) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                ", balance=" + balance +
                '}';
    }
}
