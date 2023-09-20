package com.example.quiz.securingweb.auth;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.example.quiz.entity.Employee;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class LoginUserDetails extends User{
	
	private final Employee employee;	
	
	public LoginUserDetails(Employee employee, String role) {
        //employeeテーブルの名前とパスワードでログイン認証を行う
        super(employee.getName(), 
        	employee.getPassword(),
        	AuthorityUtils.createAuthorityList(role));
        this.employee = employee;
    }

}
