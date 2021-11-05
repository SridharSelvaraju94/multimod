package com.project.web.config;

import com.project.security.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  private CustomUserDetailsService userDetailsService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
//    http.authorizeRequests()
//            .antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
//                    "/configuration/**", "/swagger-ui/**", "/javainuse-openapi/**", "/webjars/**",
//                    "/login/**", "/user/updateUser", "/uaa/oauth/token").permitAll()
//            .anyRequest().authenticated()
//            .and().httpBasic();
    http.authorizeRequests().anyRequest().authenticated().and().httpBasic();;
    http.csrf().disable();
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
            "/configuration/**", "/swagger-ui/**", "/javainuse-openapi/**",
            "/swagger-ui.html", "/webjars/**", "/login/**", "/user/updateUser", "/uaa/oauth/token",
            "/permission","/role/**","/user/**");
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
    authenticationManagerBuilder.userDetailsService(userDetailsService)
            .passwordEncoder(new BCryptPasswordEncoder());
  }

//  @Override
//  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    auth.inMemoryAuthentication()
//            .withUser("javainuse")
//            .password(passwordEncoder().encode("javainuse"))
//            .authorities("ADMIN");
//  }
//
//  @Bean
//  public PasswordEncoder passwordEncoder() {
//    return new BCryptPasswordEncoder();
//  }
}
