package com.example.kidsstore_tz.dao;

import org.springframework.lang.Nullable;

public class AuthDao {

    private String email;

    private String password;

    @Nullable
    private String name;

    public AuthDao() {
    }

    public AuthDao(String email, String password) {
        this.email = email;
        this.password = password;
        this.name = "test";
    }

    public AuthDao(String email, String password, @Nullable String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }
}
