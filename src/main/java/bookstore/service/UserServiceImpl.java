package bookstore.service;

import bookstore.dto.RegisterRequestDto;
import bookstore.dto.RegisterResponseDto;
import bookstore.entity.RoleName;
import bookstore.entity.User;
import bookstore.exception.RegistrationException;
import bookstore.mapper.UserMapper;
import bookstore.repository.RoleRepository;
import bookstore.repository.UserRepository;
import bookstore.security.AuthenticationService;
import java.util.Arrays;
import java.util.HashSet;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterResponseDto register(RegisterRequestDto requestDto) {
        if (userRepository.findByEmail(requestDto.email()).isPresent()) {
            throw new RegistrationException(
                    "The email can't be used because it's already taken: " + requestDto.email()
            );
        }
        User user = userMapper.toModel(
                requestDto,
                new HashSet<>(
                        Arrays.asList(
                                roleRepository.findRoleByName(RoleName.ROLE_USER)
                        )
                )
        );
        user.setPassword(passwordEncoder.encode(requestDto.password()));
        userRepository.save(user);
        String token = authenticationService.authenticate(requestDto);
        return userMapper.toRegisterResponseDto(user, token);
    }
}
