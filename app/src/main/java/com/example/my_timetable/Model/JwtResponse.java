package com.example.my_timetable.Model;

import java.io.Serializable;

public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private final String userRole;
    private final String email;


    public JwtResponse(String jwttoken, String userRole,String email) {
        this.jwttoken = jwttoken;
        this.userRole = userRole;
        this.email=email;
    }


    public String getToken() {
        return this.jwttoken;
    }

    public String getUserRole() {
        return userRole;
    }

    public String getEmail() {
        return email;
    }
}
