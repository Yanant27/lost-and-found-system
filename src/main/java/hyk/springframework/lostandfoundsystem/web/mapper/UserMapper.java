package hyk.springframework.lostandfoundsystem.web.mapper;

import hyk.springframework.lostandfoundsystem.domain.security.User;
import hyk.springframework.lostandfoundsystem.web.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author Htoo Yanant Khin
 **/
@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    @Mapping(target = "street", source = "address.street")
    @Mapping(target = "state", source = "address.state")
    @Mapping(target = "city", source = "address.city")
    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);
}
