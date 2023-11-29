package bookstore.mapper;

import bookstore.config.MapperConfig;
import bookstore.dto.user.RegisterRequestDto;
import bookstore.dto.user.RegisterResponseDto;
import bookstore.entity.role.Role;
import bookstore.entity.user.User;
import java.util.HashSet;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    RegisterResponseDto toDto(User user);

    User toModel(RegisterRequestDto userRequestDto, HashSet<Role> roles);
}
