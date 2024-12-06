package com.springboot.ecommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//security come to Authentication provider now need to check with database , thats why use dao authentication provider
@Configuration
public class SecurityConfig {

	//JWT=> Json web token(x.y.z)-> x: Algo+token type(hrader) , y: pay load, z: verify signature(encoded header+encoded payload+ key)
	//Authentication flow: Client sent req to server , POST /authentication with username and password-> server validate the username and password, generate the JWT using secret key -> Return the generated JWT token with value(x,y,z) to client-> client GET/ data with JWT in the header send to server-> server validate JWT using secret key-> return the response.
	//In a application-> jjwt-api, jjwt-impl dependency, jjwt-jackson(json)
	
	//always add authfailure , authsuccess here.
	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	//Here we are using AuthFailureHandlerImpl to create bean , so it is going to AuthFailureHandlerImpl service , there userserviceimpl is there , so it is going to impl service , there passwordencoder is there to coming to security class , which is making circular , so use lazy 
	@Autowired
	@Lazy  
	private AuthFailureHandlerImpl authenticationFailureHandler;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}

	//check with database
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
		http.csrf(csrf->csrf.disable()).cors(cors->cors.disable())
				.authorizeHttpRequests(req->req.requestMatchers("/user/**").hasRole("USER")
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.requestMatchers("/**").permitAll())
				.formLogin(form->form.loginPage("/signin")
						.loginProcessingUrl("/login")
//						.defaultSuccessUrl("/")
						.failureHandler(authenticationFailureHandler)
						.successHandler(authenticationSuccessHandler))
				.logout(logout->logout.permitAll());
		
		return http.build();
	}


}
