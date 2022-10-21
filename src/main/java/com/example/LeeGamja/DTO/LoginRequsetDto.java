package com.example.LeeGamja.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LoginRequsetDto {
    private String userId;
    private String userPw;
}
