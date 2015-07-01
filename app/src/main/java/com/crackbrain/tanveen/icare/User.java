package com.crackbrain.tanveen.icare;

/**
 * Created by Jakir on 5/12/2015.
 */
public class User {
    private int id;
    private String name;
    private String age;
    private String email;
    private String address;
    private String bloodGroup;
    private String mobile;

    public User() {
    }

    public User(int id, String name, String age, String email, String address, String bloodGroup, String mobile) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
        this.bloodGroup = bloodGroup;
        this.mobile = mobile;
    }

    public User(String name, String age, String email, String address, String bloodGroup, String mobile) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
        this.bloodGroup = bloodGroup;
        this.mobile = mobile;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getBlood() {
        return bloodGroup;
    }

    public String getMobile() {
        return mobile;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
