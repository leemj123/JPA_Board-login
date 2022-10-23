package com.example.LeeGamja.service;

import com.example.LeeGamja.dto.UserRequestDto;
import com.example.LeeGamja.entity.UserEntity;
import com.example.LeeGamja.enumcustom.UserRole;
import com.example.LeeGamja.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    private final Map<String,Object> sessionBox = new HashMap<>();

    private final PasswordEncoder passwordEncoder;

    public String loginv2(String userName, HttpServletResponse response){ // 세션쿠키로그인
        //세션 난수화
        String session = UUID.randomUUID().toString();
        sessionBox.put(session,userName);
        //cookie
        Cookie cookie = new Cookie("userName",session);
        cookie.setMaxAge(5*60);
        response.addCookie(cookie);
        log.info(cookie.getValue());

        return cookie.getValue();
    }
    public String signUp(UserRequestDto userRequestDto){ //회원가입
        if( userRepository.existsByUserId(userRequestDto.getUserId())){
            throw new RuntimeException("해당 ID는 이미 사용되고 있어요!");
        }
        //유저 비밀번호 인코딩
        String encodedPassEncoder = passwordEncoder.encode(userRequestDto.getUserPw());
        userRequestDto.setUserPw(encodedPassEncoder);

        log.info(userRequestDto.getUserId());
        //역할 부여
        userRequestDto.setUserRole(UserRole.USER);

        UserEntity userEntity = userRequestDto.toEntity();
        userRepository.save(userEntity);
        return "회원가입 완료";
    }

     /*public String loginv1(LoginRequsetDto loginRequsetDto, HttpServletResponse response) { //로그인

        //Cookie!
        Cookie cookie = new Cookie("userName",userEntity.getUserName());
        //Cookie를 Response
        response.addCookie(cookie);
        log.info(cookie.getValue());

        return cookie.getValue();
    }*/

}
