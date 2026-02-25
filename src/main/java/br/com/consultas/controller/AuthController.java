package br.com.consultas.controller;

import br.com.consultas.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> body) {

        String token = authService.login(
                body.get("username"),
                body.get("password")
        );

        return Map.of("token", token);
    }
}