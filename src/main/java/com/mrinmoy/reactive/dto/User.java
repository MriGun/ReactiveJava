package com.mrinmoy.reactive.dto;

public class User {

    public User(Integer userId, String firstName, String lastName) {

        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    private Integer userId;
    private String firstName;
    private String lastName;
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
