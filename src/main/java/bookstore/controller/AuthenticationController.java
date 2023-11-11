package bookstore.controller;

import bookstore.dto.LoginRequestDto;
import bookstore.dto.LoginResponseDto;
import bookstore.dto.RegisterRequestDto;
import bookstore.dto.RegisterResponseDto;
import bookstore.exception.RegistrationException;
import bookstore.security.AuthenticationService;
import bookstore.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "BookStoreAPI", description = "Endpoints for managing books")
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    @Operation(description = "Authenticate user by his login and password, returns a JWT")
    LoginResponseDto login(@Valid @RequestBody LoginRequestDto request) {
        return authenticationService.authenticate(request);
    }

    @PostMapping("/register")
    @Operation(description = "Register user, returns UserData and JWT")
    RegisterResponseDto register(@Valid @RequestBody RegisterRequestDto request)
            throws RegistrationException {
        return userService.register(request);
    }
}
