package com.example.LeeGamja.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webConfig implements WebMvcConfigurer{
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3333")
                //자원 공유를 허락할 주소 지정
                .allowedMethods("*")
                //허락할 메소드 설정(post, get, put, delete)
                .allowedHeaders("*") // 잘모르겠음
                .allowCredentials(false);// 다른 서버에서의 쿠키 요청을 허용할건가? 물론 어림없지 보안문제 컷
                //.maxAge()//preflight? ? ?
        /*
    preflight
    Simple request가 아닌 요청 메시지보다 먼저 보내는 메시지로, 브라우저는 응답값으로 실제 데이터 전송 여부를 판단.

    CORS는 응답이 Access-Control-Allow-Credentials: true 을 가질 경우, Access-Controll-Allow-Origin의 값으로 *를 사용하지 못하게 막고 있다
    Access-Control-Allow-Credentials: true를 사용하는 경우는 사용자 인증이 필요한 리소스 접근이 필요한 경우인데,
    만약 Access-Control-Allow-Origin: *를 허용한다면,
    CSRF 공격에 매우 취약해져 악의적인 사용자가 인증이 필요한 리소스를 마음대로 접근할 수 있음.
 */

    }
}
