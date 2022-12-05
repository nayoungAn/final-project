package com.greedy.onoff.configuration;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.greedy.onoff.jwt.JwtAccessDenieHandler;
import com.greedy.onoff.jwt.JwtAuthenticationEntryPoint;
import com.greedy.onoff.jwt.TokenProvider;




@EnableWebSecurity
public class SecurityConfig {
	
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private final JwtAccessDenieHandler jwtAccessDenieHandler;
	private final TokenProvider tokenProvider;
	
	public SecurityConfig(TokenProvider tokenProvider, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtAccessDenieHandler jwtAccessDenieHandler) {
		this.tokenProvider = tokenProvider;
		this.jwtAccessDenieHandler = jwtAccessDenieHandler;
		this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
	}
	
	// 외부에서 이미지 파일에 접근 가능 하도록 설정
		@Bean
		public WebSecurityCustomizer configure() {
			return (web) -> web.ignoring().antMatchers("/memberimgs/**");
		}
		
	@Bean
	public PasswordEncoder passwordEncoder() { 
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		 return http
		         .csrf()
		         	.disable()
		         .exceptionHandling()
		         	.authenticationEntryPoint(jwtAuthenticationEntryPoint)
		         	.accessDeniedHandler(jwtAccessDenieHandler)
		         .and()	 
		         .sessionManagement()
		             .sessionCreationPolicy(SessionCreationPolicy.STATELESS) 
		         .and()
		             .authorizeRequests()
		             .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
		             .antMatchers("/auth/**").permitAll()
		             .antMatchers("/ono/**").hasAnyRole("TEACHER", "ADMIN", "STUDENT")
		         .and()
		         	.cors()
		         .and()
		         	.apply(new JwtSecurityConfig(tokenProvider)) 
		         .and()
		         	.build();
		 
	}
	

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000" ));
        configuration.setAllowedMethods(Arrays.asList("GET", "PUT", "POST", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Origin", "Content-Type", "Access-Control-Allow-Headers", "Authorization", "X-Requested-With"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
