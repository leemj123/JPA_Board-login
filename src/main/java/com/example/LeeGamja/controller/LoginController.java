package com.example.LeeGamja.controller;

import com.example.LeeGamja.dto.LoginRequsetDto;
import com.example.LeeGamja.dto.userDto.UserRequestDto;
import com.example.LeeGamja.entity.UserEntity;
import com.example.LeeGamja.error.exception.UnAuthorizedException;
import com.example.LeeGamja.repository.UserRepository;
import com.example.LeeGamja.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import static com.example.LeeGamja.error.ErrorCode.*;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @PostMapping("/login")
    public String login(@RequestBody LoginRequsetDto loginRequsetDto,
                        HttpServletResponse response){
        UserEntity userEntity = userRepository.findByUserId(loginRequsetDto.getUserId());
        if (userEntity == null) {
            throw new UnAuthorizedException(NONEXISTENT_EXCEPTION,"E0012");
        }
        if ( !passwordEncoder.matches(loginRequsetDto.getUserPw(),userEntity.getUserPw()) ) {
            throw new UnAuthorizedException(NOT_APPLICABLE_EXCEPTION,"E0013");
        }

        return loginService.login(userEntity.getUserName(), response);
    }

    @PostMapping("/signup")
    public void signUp(@RequestBody UserRequestDto userRequestDto){
        loginService.signUp(userRequestDto);}

    @PostMapping("/logout")
    public String logout(@CookieValue(value = "userName",required = true)Cookie userName,
                         HttpServletResponse response){
        if(userName == null){
            throw new UnAuthorizedException(ACCESS_DENIED_EXCEPTION,"E0010");
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
