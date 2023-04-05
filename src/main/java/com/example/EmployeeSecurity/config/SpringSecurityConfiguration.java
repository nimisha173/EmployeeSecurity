package com.example.EmployeeSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {
  protected void configure(AuthenticationManagerBuilder auth)throws Exception{
	  auth.inMemoryAuthentication().withUser("sharath").password("sharath@123").roles("ADMIN");
	  auth.inMemoryAuthentication().withUser("sharath1").password("sharath@21").roles("USER");
  }
  
//  protected void configure(HttpSecurity http)throws Exception{
//	  http.csrf().disable();
//	  http.authorizeRequests().anyRequest().fullyAuthenticated().and().httpBasic();
//  }
  
  protected void configure(HttpSecurity http)throws Exception{
	  http.csrf().disable();
	  http.authorizeRequests().antMatchers("/rest/**").hasAnyRole("ADMIN").anyRequest()
	  .fullyAuthenticated().and().httpBasic();
  }
  @Bean
  public static NoOpPasswordEncoder passwordEncoder() {
	return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance()  ;
	  
  }
}
