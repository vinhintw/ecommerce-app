package com.example.appfinalproject_11131415.Domain;

public class User {
    private String name;
    private String email;
    private String mobile;
    private String address;
    private String postalCode;

    public User(String name, String mobile, String email, String address, String postalCode) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
        this.postalCode = postalCode;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
