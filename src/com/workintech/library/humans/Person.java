package com.workintech.library.humans;

import com.workintech.library.enums.Roles;

public class Person {
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private Roles roles;

    //farklı farklı constructorlara ihtiyacım oldugu için overloading uyguladım
    public Person(String name, String surname, String phoneNumber, String email, Roles roles) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.roles = roles;
    }

    public Person(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
    public Person(String name, String surname,Roles roles) {
        this.name = name;
        this.surname = surname;
        this.roles = roles;
    }

    public Person(String name) {
        this.name = name;
    }

    //GETTER SETTER START******************

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    //GETTER SETTER END*************************
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }
}
