package com.hsc.cat.security;

import org.aspectj.weaver.ast.And;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.hsc.cat.service.UserSecurityService;
import com.hsc.cat.utilities.Roles;






@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


	
//	@Autowired
//	@Lazy
//	private UserDetailsService userDetailsService;
//	

//	@Autowired
//	private UserSecurityService userSecurityService;
  
	  @Bean
	    public BCryptPasswordEncoder bCryptPasswordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	@Autowired
    private UserDetailsService userDetailsService;
	
	 @Autowired
	 private JwtAuthenticationEntryPoint unauthorizedHandler;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		 auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
    
	 @Bean
	    public JwtFilter authenticationTokenFilterBean() throws Exception {
	        return new JwtFilter();
	    }
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.authorizeRequests()
		.antMatchers(
                HttpMethod.GET,
                "/",
                "/**/*.html",
                "/**/*.css",
                "/**/*.js"
               
        ).permitAll()
		.antMatchers("/cat/auth/**").permitAll()
		.antMatchers("/auth/**").permitAll().anyRequest().authenticated();
//		.antMatchers("/auth/**").permitAll()
//		//.antMatchers("/secure/**").access("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_MANAGER')")
//		
//		.antMatchers("/secure/**").permitAll().anyRequest().authenticated()
//		
//		.antMatchers("/manager/**").access("hasRole('ROLE_MANAGER')");
		
		
//		http.csrf().disable().
//		formLogin().failureUrl("/auth/validateUser").defaultSuccessUrl("/")./*.httpBasic().and()*/
//		and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/secure/logout")).logoutSuccessUrl("/auth/logoutSuccess").deleteCookies("remember-me").permitAll();
//		
//		
//		http.headers().cacheControl();
		
		http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
		http.headers().cacheControl();
	}


}