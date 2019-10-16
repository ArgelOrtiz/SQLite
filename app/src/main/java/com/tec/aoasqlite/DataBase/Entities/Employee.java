package com.tec.aoasqlite.DataBase.Entities;

import java.util.Objects;

public class Employee {

    private long id;
    private int code;
    private String first_name;
    private String last_name;
    private int phone;
    private int balance;

    public Employee(){

    }

    public Employee(int id, int code, String first_name, String last_name, int phone, int balance){
        this.id         = id;
        this.code       = code;
        this.first_name = first_name;
        this.last_name  = last_name;
        this.phone      = phone;
        this.balance    = balance;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", code=" + code +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", phone=" + phone +
                ", balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return getId() == employee.getId() &&
                getCode() == employee.getCode() &&
                getPhone() == employee.getPhone() &&
                getBalance() == employee.getBalance() &&
                getFirst_name().equals(employee.getFirst_name()) &&
                getLast_name().equals(employee.getLast_name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCode(), getFirst_name(), getLast_name(), getPhone(), getBalance());
    }
}
