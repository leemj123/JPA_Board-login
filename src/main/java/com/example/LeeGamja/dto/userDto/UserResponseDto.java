package com.example.LeeGamja.dto.userDto;

import com.example.LeeGamja.entity.UserEntity;
import com.example.LeeGamja.enumcustom.UserRole;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private String userName;
    private String userId;
    private String userPw;
    private UserRole userRole;
    public UserResponseDto(UserEntity userEntity){
        this.userName =userEntity.getUserName();
        this.userId = userEntity.getUserId();
        this.userPw = userEntity.getUserPw();
        this.userRole = userEntity.getUserRole();
    }
}
