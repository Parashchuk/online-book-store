package bookstore.controller;

import bookstore.dto.LoginRequestDto;
import bookstore.dto.LoginResponseDto;
import bookstore.dto.RegisterRequestDto;
import bookstore.dto.RegisterResponseDto;
import bookstore.security.AuthenticationService;
import bookstore.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    LoginResponseDto login(@Valid @RequestBody LoginRequestDto request) {
        return authenticationService.authenticate(request);
    }

    @PostMapping("/register")
    RegisterResponseDto register(@Valid @RequestBody RegisterRequestDto request) {
        return userService.register(request);
    }
}
