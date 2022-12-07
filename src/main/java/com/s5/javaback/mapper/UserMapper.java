package com.s5.javaback.mapper;

import com.google.firebase.auth.UserRecord;
import com.s5.javaback.model.entity.User;
import com.s5.javaback.model.request.UserRequest;
import com.s5.javaback.model.response.UserResponse;
import com.s5.javaback.security.model.UserFirebase;
import com.s5.javaback.util.constants.DateFormatConstants;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "createdAt", dateFormat = DateFormatConstants.DATE_TIME_FORMAT)
    UserResponse toResponse(User entity);
    List<UserResponse> toResponses(List<User> entities);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    User toEntity(UserRequest request);

    @Mapping(target = "turnList", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "image.imageUrl", source = "image")
    User toEntity(UserFirebase firebase);

    @Mapping(target = "name", source = "displayName")
    @Mapping(target = "image", source = "photoUrl")
    UserFirebase toFirebase(UserRecord record);

}
