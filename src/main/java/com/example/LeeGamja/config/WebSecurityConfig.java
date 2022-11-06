package com.example.LeeGamja.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//사용자 비밀번호 암호화

@Configuration//구성이란 뜻으로 가시적인 설정파일이다. 이 파일에 있는 빈을 찾아서 컨테이너에 넣어라
@EnableWebSecurity//
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {//
    @Bean// 수동으로 빈을 설정해주면 밑의 메소드이름이 빈의 이름이 된다.
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
        //PasswordEncoder의 인터페이스를 구현한게 비크립트 인코더다.
        //내장 메서드들(비크립토)
            //.encode -> 패스워드를 8바이트 해쉬로 랜덤하게 암호화, 반환타입 String 똑같은 비밀번로라도 매번 다른 인코딩 된 문자열을 반환한다.
            //.matchers-> (유저가 입력한 패스워드, 인코딩된 패스워드)의 일치 여부를 확인, 반환 타입은 boolean
            //.upgradeEncoding->  인코딩된걸 한번 더 인코딩 음...어캐 사용할지 모르니 보류
    }
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        //cors -> Cross-Origin Resource Sharing
        httpSecurity
                .cors().disable()
                .csrf().disable()
                //Cross-Site Request Forgery 이용자가 의도하지 않은 요청을 통한 공격
                //인터넷 사용자가 자신의 의지와는 무관하게 공격자가 의도한 행위(등록,수정,삭제)를 특정 웹사이트에 요청하도록 만드는 공격
                .formLogin().disable()
                //시큐리티에서 기본적으로 제공하는 로그인 화면을 쓰겠냐
                .headers().frameOptions().disable();
                //Http 통신 할때 헤더,바디,스테이터스 3개 중 헤더 설정
                //헤더 내의 프레임 옵션 관련한 모든 걸 비활성화 하겠다
                //iframe(화면 분할 관련)설정인데 쓸 이유가 없으니 disable
                //현재 여러 브라우저가 iframe에 대한 지원이 끊어지고있다.
                //에러가 날때는 disable 대신에 sameOrifin()을 달아주면 된다.
        httpSecurity.sessionManagement(
                s->s.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                        //항상 세션을 생성하겠다.
                        .sessionFixation(sf->sf.changeSessionId())
                        //인증할때마다 새로운 세션아이디를 발급하겠다.
                        .maximumSessions(1)
                        //하나의 계정으로 접속시 세션은 하나만 발급하겠다
                        //-1은 무한 동시접속 가능
                        .maxSessionsPreventsLogin(true)
                        //동일 계정으로 로그인 시도시 기존의 세션을 만료시키고 새로 만들어서 로그인시킨다.
                        //.expiredUrl()
        );
    }
}
