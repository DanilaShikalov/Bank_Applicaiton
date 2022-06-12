package com.example.bank_applicaiton;

public class Client {
    private int id;
    private String name;
    private String surname;
    private String applicationNum;

    public Client(int id, String name, String surname, String applicationNum) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.applicationNum = applicationNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getApplicationNum() {
        return applicationNum;
    }

    public void setApplicationNum(String applicationNum) {
        this.applicationNum = applicationNum;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", applicationNum='" + applicationNum;
    }
}
