package com.example.quiz.securingweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.quiz.entity.Employee;
import com.example.quiz.repository.EmployeeServiceRepository;
import com.example.quiz.securingweb.auth.LoginUserDetails;

@Component
@Transactional
public class LoginUserDetailsService implements UserDetailsService {
	
	@Autowired
	EmployeeServiceRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Employee employee = repository.selectByName(username);
		
		if (employee == null) {
			throw new UsernameNotFoundException("Wrong email or password");
		}
		
		String role = "ROLE_ADMIN";
		
		return new LoginUserDetails(employee, role);
	}

}
