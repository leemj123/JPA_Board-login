package com.example.LeeGamja.Service;

import com.example.LeeGamja.DTO.LoginRequsetDto;
import com.example.LeeGamja.DTO.UserRequestDto;
import com.example.LeeGamja.Entity.BoardListEntity;
import com.example.LeeGamja.Entity.UserEntity;
import com.example.LeeGamja.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    private Map<String,Object> sessionBox = new HashMap<>();

    public String loginv2(String userName, HttpServletResponse response){
        //세션 난수화
        String session = UUID.randomUUID().toString();
        sessionBox.put(session,userName);
        //cookie
        Cookie cookie = new Cookie("userName",session);
        response.addCookie(cookie);
        log.info(cookie.getValue());

        return cookie.getValue();
    }
    public String signUp(UserRequestDto userRequestDto){ //회원가입
        if( userRepository.existsByUserId(userRequestDto.getUserId())){
            throw new RuntimeException("해당 ID는 이미 사용되고 있어요!");
        }
        UserEntity userEntity = userRequestDto.toEntity();
        userRepository.save(userEntity);
        return "회원가입 완료";
    }

    public String loginv1(LoginRequsetDto loginRequsetDto, HttpServletResponse response) { //로그인

        if (!(userRepository.existsByUserId(loginRequsetDto.getUserId()))) {
            return "해당하는 계정이 없습니다.";
        }
        UserEntity userEntity = userRepository.findByUserId(loginRequsetDto.getUserId());
        if ( !(loginRequsetDto.getUserPw()).equals(userEntity.getUserPw()) ) {
            return "비밀번호가 맞지 않습니다.";
        }
        //Cookie!
        Cookie cookie = new Cookie("userName",userEntity.getUserName());
        //Cookie를 Response
        response.addCookie(cookie);
        log.info(cookie.getValue());

        return cookie.getValue();
    }

}
