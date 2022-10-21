package com.example.LeeGamja.Controller;

import com.example.LeeGamja.DTO.LoginRequsetDto;
import com.example.LeeGamja.DTO.UserRequestDto;
import com.example.LeeGamja.Entity.UserEntity;
import com.example.LeeGamja.Repository.UserRepository;
import com.example.LeeGamja.Service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession; //세션 인터페이스

@RestController
@Slf4j
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    private final UserRepository userRepository;
    @PostMapping("/login")
    public String login(@RequestBody LoginRequsetDto loginRequsetDto,
                        HttpServletResponse response){
        log.info(loginRequsetDto.getUserId());
        if (!(userRepository.existsByUserId(loginRequsetDto.getUserId()))) {
            throw new RuntimeException("해당 계정이 없습니다.");
        }
        UserEntity userEntity = userRepository.findByUserId(loginRequsetDto.getUserId());

        if ( !(loginRequsetDto.getUserPw()).equals(userEntity.getUserPw()) ) {
            throw new RuntimeException("비밃번호가 맞지 않습니다.");
        }

        return loginService.loginv2(userEntity.getUserName(), response);
    }

    @PostMapping("/signup")
    public String signUp(@RequestBody UserRequestDto userRequestDto){
        return loginService.signUp(userRequestDto);}

    @PostMapping("/logout")
    public String logout(@CookieValue(value = "userName",required = true)Cookie userName,
                         HttpServletResponse response){
        if(userName == null){
            return "로그인이 되어있지 않습니다";
        }
        //쿠키 삭제
        //널인 쿠키를 만들어서
        Cookie cookie = new Cookie("userName",null);
        //max -age 0으로 만들어서 쿠키의 세션을 종료시킴
        cookie.setMaxAge(0);
        //0인 쿠키 날리기
        response.addCookie(cookie);

        //세션 로그아웃 login(HttpServletRequest request)
        /*HttpSession session = request.getSession(false);
        if(session != null){session.invalidate();} // 세션 날리기*/
        return "로그아웃완료";
    }
}
