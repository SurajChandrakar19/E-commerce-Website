package com.sastaa.controller;
//
//import org.springframework.web.bind.annotation.*;
//import org.springframework.http.ResponseEntity;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
//        // Logic for username-password or token-based login
//        return ResponseEntity.ok("Login successful!");
//    }
//
//    @PostMapping("/signup")
//    public ResponseEntity<String> signup(@RequestBody SignupRequest signupRequest) {
//        // Logic for new user registration
//        return ResponseEntity.ok("User registered successfully!");
//    }
//
//    @GetMapping("/oauth2/redirect")
//    public ResponseEntity<String> handleOAuth2Login(@RequestParam String code, @RequestParam String provider) {
//        // Logic for OAuth2 authentication with Google/GitHub
//        return ResponseEntity.ok("OAuth2 login successful!");
//    }
//}
