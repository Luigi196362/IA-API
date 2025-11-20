package com.ia.api.Login;

import org.springframework.stereotype.Service;

import com.ia.api.Login.Dto.LoginDto;
import com.ia.api.User.User;
import com.ia.api.User.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    public String login(LoginDto request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return "Inicio de sesion exitoso";
    }

    public String register(LoginDto request) {

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("El nombre de usuario ya existe");
        }

        User newUser = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .build();

        userRepository.save(newUser);

        return "Registro exitoso";
    }
}
