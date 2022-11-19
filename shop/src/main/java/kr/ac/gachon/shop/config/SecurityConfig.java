package kr.ac.gachon.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // Spring Security 5.7.0-M2 부터 기존 WebSecurityConfigureAdapter 방식에서 SecurityFilterChain 으로 변경 권장
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //로그인과 가장 중요하게 관련된 필터체인
        // 인증이 필요한 요청폼 설정
        http.formLogin()
                .loginPage("/members/login")
                .defaultSuccessUrl("/")
                .usernameParameter("email")
                .failureUrl("/members/login/error")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
                .logoutSuccessUrl("/")
        ;
        http.authorizeRequests()
                .mvcMatchers("/css/**", "/js/**", "/img/**").permitAll()
                .mvcMatchers("/", "/members/**", "/item/**", "/images/**").permitAll()
                .mvcMatchers("/admin/**").hasRole("ADMIN")
                //admin이후의 페이지는 로그인을 해서 admin을 받을 user만 들어갈 수 있다.
                .anyRequest().authenticated()
        ;
        //인증 실패시 라우팅
        http.exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
        ;

        return http.build();
    }
    //빈등록
    //비밀번호 암호화시킬때 BCryptPasswordEncoder 요거가지고 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}