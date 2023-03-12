package com.ivx;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author skyler
 * @apiNote
 * @since 2023/3/3 11:40
 */
// @Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Mapper
public interface UserConvert {
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserVO entity2VO(UserEntity userEntity);

    List<UserVO> entity2VOList(List<UserEntity> userEntityList);

    UserEntity dto2Entity(UserDTO userDTO);

    List<UserEntity> dto2EntityList(List<UserDTO> userDTOList);
}
