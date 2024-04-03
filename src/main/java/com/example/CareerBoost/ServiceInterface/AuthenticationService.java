package com.example.CareerBoost.ServiceInterface;

import com.example.CareerBoost.Dto.JwtAuthenticationResponse;
import com.example.CareerBoost.Dto.LoginRequest;
import com.example.CareerBoost.Dto.SignUpRequest;
import com.example.CareerBoost.Entity.User;

public interface AuthenticationService {
    User signup(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signIn(LoginRequest loginRequest);
}
