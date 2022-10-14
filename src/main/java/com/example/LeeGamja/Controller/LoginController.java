package com.example.LeeGamja.Controller;

import com.example.LeeGamja.DTO.UserRequestDto;
import com.example.LeeGamja.Service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    @GetMapping("/login")
    public String login(@RequestBody UserRequestDto userRequestDto){
        return loginService.checkUser(userRequestDto);
    }
    @PostMapping("/signup")
    public String signUp(@RequestBody UserRequestDto userRequestDto){
        return loginService.signUp(userRequestDto);
    }
}
