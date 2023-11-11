package bookstore.service;

import bookstore.dto.RegisterRequestDto;
import bookstore.dto.RegisterResponseDto;
import bookstore.exception.RegistrationException;

public interface UserService {
    RegisterResponseDto register(RegisterRequestDto requestDto) throws RegistrationException;
}
