package com.org.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.org.service.impl.IUserDtlsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

	@Autowired
	IUserDtlsServiceImpl iuserDtlsServiceImpl;
	
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(iuserDtlsServiceImpl)
            .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
   		http
   		.authorizeHttpRequests((req) ->

   		req.antMatchers("/admin/login").permitAll()
   		.antMatchers("admin/**").hasRole("Admin")
   		.antMatchers("customer/**") .hasAnyRole("Admin", "Customer")
   		 .antMatchers("/home").hasAnyRole("Admin", "User")

   		.anyRequest().authenticated()
   		).formLogin();

   		return http.build();
    }
	
}
