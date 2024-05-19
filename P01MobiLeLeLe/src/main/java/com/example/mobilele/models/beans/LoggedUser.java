package com.example.mobilele.models.beans;

import com.example.mobilele.models.enums.Role;

public class LoggedUser {

    private String id;
    private String username;
    private Role role;

    public String getId() {
        return id;
    }

    public LoggedUser setId(String id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public LoggedUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public LoggedUser setRole(Role role) {
        this.role = role;
        return this;
    }

    public void clearFields() {
        this.id = null;
        this.username = null;
        this.role = null;
    }
}
