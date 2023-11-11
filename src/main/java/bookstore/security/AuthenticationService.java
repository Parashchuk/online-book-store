package bookstore.security;

import bookstore.dto.LoginRequestDto;
import bookstore.dto.LoginResponseDto;
import bookstore.dto.RegisterRequestDto;
import bookstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationService {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public LoginResponseDto authenticate(LoginRequestDto requestDto) {
        userRepository.findByEmail(requestDto.email()).orElseThrow(
                () -> new UsernameNotFoundException("User with such an email doesn't exist: "
                        + requestDto.email())
        );

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestDto.email(), requestDto.password())
        );
        String token = jwtUtil.generateToken(authentication.getName());
        return new LoginResponseDto(token);
    }

    public String authenticate(RegisterRequestDto requestDto) {
        return jwtUtil.generateToken(requestDto.email());
    }
}
