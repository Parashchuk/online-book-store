package bookstore.service;

import bookstore.dto.user.RegisterRequestDto;
import bookstore.dto.user.RegisterResponseDto;
import bookstore.exception.RegistrationException;

public interface UserService {
    RegisterResponseDto register(RegisterRequestDto requestDto) throws RegistrationException;
}
