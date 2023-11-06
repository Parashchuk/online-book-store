package bookstore.service;

import bookstore.dto.RegisterRequestDto;
import bookstore.dto.RegisterResponseDto;

public interface UserService {
    RegisterResponseDto register(RegisterRequestDto requestDto);
}
