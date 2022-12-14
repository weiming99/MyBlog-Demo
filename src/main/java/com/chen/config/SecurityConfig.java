package com.chen.config;

import com.chen.entity.Menu;
import com.chen.filter.JwtAuthenticationTokenFilter;
import com.chen.mapper.MenuMapper;
import com.chen.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Resource
    MenuMapper menuMapper;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return MD5Util.encode((String) charSequence);
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return s.equals(MD5Util.encode((String) charSequence));
            }
        };
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authenticated = http
                //??????csrf
                .csrf().disable()
                //?????????Session??????SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // ?????????????????? ??????????????????
                .antMatchers("/user/login").anonymous()
                // druid?????? ??????
                .antMatchers("/druid/**").permitAll()
                // druid?????? ??????
                .antMatchers("/captchaImage").permitAll();
        // ???????????????????????????????????????????????????

        for (Menu menu : menuMapper.selectAllByStatus("0")) {
            authenticated.antMatchers(menu.getPath()).hasAuthority(menu.getPerms());
        }

        authenticated.anyRequest().authenticated();

        //???token???????????????????????????????????????
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).
                accessDeniedHandler(accessDeniedHandler);


        //????????????
        http.cors();

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}