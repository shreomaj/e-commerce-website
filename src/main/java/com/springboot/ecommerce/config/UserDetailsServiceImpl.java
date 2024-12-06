package com.springboot.ecommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.ecommerce.entity.UserDtls;
import com.springboot.ecommerce.repository.UserRepository;

// Spring Boot is unable to find a bean of type AuthenticationSuccessHandler that is required by the SecurityConfig class.
//This typically occurs when the AuthenticationSuccessHandler is not properly defined or registered as a Spring bean
//in your application context.
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserDtls user = userRepository.findByEmail(username);

		if (user == null) {
			throw new UsernameNotFoundException("user not found");
		}
		return new CustomUser(user);
	}

}
