package com.rangjin.restapi.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // Rest API 이므로 기본 설정 사용 X(기본 설정은 비 인증시 로그인 폼으로 리다이렉트)
                .httpBasic().disable()
                // Rest API 이므로 csrf 보안이 필요 없음
                .csrf().disable()
                // jwt token 으로 인증하므로 세션은 필요하지 않음
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()
                        // 가입 및 인증 주소는 누구나 접근 가능
                        .antMatchers("/*/signin", "/*/signup", "/*/signin/**", "/*/signup/**", "/social/**").permitAll()
                        // helloworld 로 시작하는 GET 요청 리소스는 누구나 접근 가능
                        .antMatchers(HttpMethod.GET, "/exception/**", "helloworld/**").permitAll()
                        // 그 외 나머지 요청은 인증된 사용자만 접근 가능
                        .anyRequest().hasRole("USER")
                .and()
                    .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .and()
                    .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())
                .and()
                    // jwt token 필터를 id/password 인증 필터 전에 넣음
                    .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // swagger 리소스는 체크 무시
        web.ignoring().antMatchers("/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html", "/webjars/**", "/swagger/**");
    }

}
