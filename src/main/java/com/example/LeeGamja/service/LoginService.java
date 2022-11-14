package com.example.LeeGamja.service;

import com.example.LeeGamja.dto.userDto.UserRequestDto;
import com.example.LeeGamja.entity.UserEntity;
import com.example.LeeGamja.enumcustom.UserRole;
import com.example.LeeGamja.error.exception.UnAuthorizedException;
import com.example.LeeGamja.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static com.example.LeeGamja.error.ErrorCode.DUPLICATE_USER_EXCEPTION;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    public static final Map<String,String> sessionBox = new HashMap<>();

    private final PasswordEncoder passwordEncoder;

    public String login(String userName, HttpServletResponse response){ // 세션쿠키로그인
        //세션 난수화
        String session = userName;
        sessionBox.put(session,userName);
        //cookie
        Cookie cookie = new Cookie("userName",session);
        cookie.setMaxAge(5*60);
        response.addCookie(cookie);

        return cookie.getValue();
    }
    public void signUp(UserRequestDto userRequestDto){ //회원가입
        if( userRepository.existsByUserId(userRequestDto.getUserId())){
            throw new UnAuthorizedException(DUPLICATE_USER_EXCEPTION,"E0011");
        }
        //유저 비밀번호 인코딩
        String encodedPassEncoder = passwordEncoder.encode(userRequestDto.getUserPw());
        userRequestDto.setUserPw(encodedPassEncoder);

        //역할 부여
        userRequestDto.setUserRole(UserRole.USER);

        UserEntity userEntity = userRequestDto.toEntity();
        userRepository.save(userEntity);
    }

}
