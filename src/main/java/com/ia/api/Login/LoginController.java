package com.ia.api.Login;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ia.api.Login.Dto.LoginDto;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto request) {
        return ResponseEntity.ok(loginService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody LoginDto request) {
        return ResponseEntity.ok(loginService.register(request));
    }

}
