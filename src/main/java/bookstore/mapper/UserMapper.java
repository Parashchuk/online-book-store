package bookstore.mapper;

import bookstore.dto.RegisterRequestDto;
import bookstore.dto.RegisterResponseDto;
import bookstore.entity.role.Role;
import bookstore.entity.user.User;
import java.util.HashSet;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        implementationPackage = "<PACKAGE_NAME>.impl"
)
public interface UserMapper {
    RegisterResponseDto toRegisterResponseDto(User user, String token);

    User toModel(RegisterRequestDto userRequestDto, HashSet<Role> roles);
}
