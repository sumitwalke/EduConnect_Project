package com.wecp.progressive.controller;

import com.wecp.progressive.dto.LoginRequest;
import com.wecp.progressive.dto.LoginResponse;
import com.wecp.progressive.dto.UserRegistrationDTO;
import com.wecp.progressive.entity.User;
import com.wecp.progressive.jwt.JwtUtil;
import com.wecp.progressive.service.impl.UserLoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserLoginController {

    @Autowired
    private UserLoginServiceImpl userLoginService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        try {
            userLoginService.registerUser(userRegistrationDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            UserDetails userDetails = userLoginService.loadUserByUsername(loginRequest.getUsername());
            String token = jwtUtil.generateToken(loginRequest.getUsername());
            User user = userLoginService.getUserByUsername(loginRequest.getUsername());
            Integer studentId = user.getStudent() != null ? user.getStudent().getStudentId() : null;
            Integer teacherId = user.getTeacher() != null ? user.getTeacher().getTeacherId() : null;
            return ResponseEntity.ok(new LoginResponse(token, user.getRole(), user.getUserId(), studentId, teacherId));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body(new LoginResponse("Invalid username or password", null, null, null, null));
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserDetails(@PathVariable int userId) {
        try {
            User user = userLoginService.getUserDetails(userId);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
