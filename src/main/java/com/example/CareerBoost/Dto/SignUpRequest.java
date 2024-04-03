package com.example.CareerBoost.Dto;

import com.example.CareerBoost.Entity.Role;
import lombok.Data;

@Data

public class SignUpRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;

    public SignUpRequest(String firstName, String lastName, String email, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        if(role == Role.Investor || role ==Role.Student)
        {
            this.role = role;

        }

    }
}
