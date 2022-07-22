package kr.co.clozet.auth.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * packageName:kr.co.clozet.config
 * fileName        :WebSecurityConfig.java
 * author          : sungsuhan
 * date            :2022-05-23
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-05-23           sungsuhan      최초 생성
 **/
@Configuration //인터셉터역할
public class AuthConfiguration extends WebSecurityConfigurerAdapter {
    //회원수정 후 세션 유지하기 위해
    @Bean @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    // 보안 무시할 수 있는것들 (홈화면)
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(HttpMethod.OPTIONS, "*/**")
                .antMatchers("/");
    }
    // permitAll 로 허용할 것만 코딩, 나머지는 잠겨있다
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests()
                .antMatchers("/api/weather").permitAll()
                .antMatchers("/users/join").permitAll()
                .antMatchers("/users/login").permitAll()
                .antMatchers("/users/update").permitAll()
                .antMatchers("/users/delete").permitAll()
                .antMatchers("/users/username").permitAll()
                .antMatchers("/users/password").permitAll()
                .antMatchers("/users/exists").permitAll()
                .antMatchers("/users/token").permitAll()
                .antMatchers("/articles/write").permitAll()
                .antMatchers("/articles/list").permitAll()
                .antMatchers("/articles/myList").permitAll()
                .antMatchers("/articles/update").permitAll()
                .antMatchers("/articles/delete").permitAll()
                .antMatchers("/articles/write/qna").permitAll()
                .antMatchers("/articles/list/qna").permitAll()
                .antMatchers("/articles/myList/qna").permitAll()
                .antMatchers("/articles/list/comment").permitAll()
                .antMatchers("/articles/delete/comment").permitAll()
                .antMatchers("/files/upload").permitAll()
                .antMatchers("/files/findAll").permitAll()
                .antMatchers("/articles/findByToken").permitAll()
                .antMatchers("/articles/findByQnaDateASC").permitAll()
                .antMatchers("/articles/deleteArticle").permitAll()
                .antMatchers("/clothes/classification").permitAll()
                .antMatchers("/articles/posts/{title}").permitAll()
                .anyRequest().authenticated();
        http.exceptionHandling().accessDeniedPage("/users/login");
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/users/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true);

    }
}






