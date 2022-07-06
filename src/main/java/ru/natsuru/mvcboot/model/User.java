package ru.natsuru.mvcboot.model;

import javax.persistence.*;
@Entity
@Table(name = "superusers")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String surName;
    @Column
    private int socialNumber;

    public User() {
    }

    public User(String name, String surName, int socialNumber) {
        this.name = name;
        this.surName = surName;
        this.socialNumber = socialNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public int getSocialNumber() {
        return socialNumber;
    }

    public void setSocialNumber(int socialNumber) {
        this.socialNumber = socialNumber;
    }

    @Override
    public String toString() {
        return id + ". Користувач " + getName() + " " + getSurName() + " з спильнотним номером " + getSocialNumber();
    }
}
