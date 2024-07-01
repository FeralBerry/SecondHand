package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.user.RegisterDTO;
import ru.skypro.homework.dto.user.UpdateUserDTO;
import ru.skypro.homework.dto.user.UserDTO;
import ru.skypro.homework.entity.User;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "avatar", ignore = true)
    @Mapping(target = "ads", ignore = true)
    @Mapping(target = "comments", ignore = true)
    User registerDTOToUser(RegisterDTO registerDTO);

    @Mapping(target = "email", source = "user.username")
    @Mapping(target = "avatar", expression = "java(user.getAvatar() != null ? \"/image/\" + user.getAvatar().getId() : null)")
    UserDTO toUserDTO(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "avatar", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "ads", ignore = true)
    @Mapping(target = "comments", ignore = true)
    void updateUserDTOToUser(UpdateUserDTO updateUserDTO, @MappingTarget User user);
}
