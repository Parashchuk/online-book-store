package bookstore.service.impl;

import bookstore.dto.RegisterRequestDto;
import bookstore.dto.RegisterResponseDto;
import bookstore.entity.role.Role;
import bookstore.entity.user.User;
import bookstore.exception.RegistrationException;
import bookstore.mapper.UserMapper;
import bookstore.repository.RoleRepository;
import bookstore.repository.UserRepository;
import bookstore.service.UserService;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterResponseDto register(RegisterRequestDto requestDto)
            throws RegistrationException {
        existsByEmail(requestDto);
        User user = userMapper.toModel(
                requestDto,
                new HashSet<>(
                        Set.of(
                                roleRepository.findRoleByName(Role.RoleName.ROLE_USER)
                        )
                )
        );
        user.setPassword(passwordEncoder.encode(requestDto.password()));
        userRepository.save(user);
        return userMapper.toRegisterResponseDto(user);
    }

    private void existsByEmail(RegisterRequestDto requestDto) throws RegistrationException {
        if (userRepository.findByEmail(requestDto.email()).isPresent()) {
            throw new RegistrationException(
                    "The email is not available"
            );
        }
    }
}
