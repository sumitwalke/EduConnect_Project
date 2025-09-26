package com.wecp.progressive.controller;

import com.wecp.progressive.dto.LoginRequest;
import com.wecp.progressive.dto.LoginResponse;
import com.wecp.progressive.dto.UserRegistrationDTO;
import org.springframework.http.ResponseEntity;

public class UserLoginController {
    public ResponseEntity<?> registerUser(UserRegistrationDTO registrationDTO) {
        return null;
    }

    public ResponseEntity<LoginResponse> loginUser(LoginRequest loginRequest) {
        return null;
    }

    public ResponseEntity<?> getUserDetails(int userId) {
        return null;
    }
}
