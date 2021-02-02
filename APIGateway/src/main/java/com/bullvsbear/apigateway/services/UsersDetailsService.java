package com.bullvsbear.apigateway.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bullvsbear.apigateway.models.AuthenticationRequest;

@Service
public class UsersDetailsService implements UserDetailsService{
	
	@Autowired	
	RestTemplate restTemplate;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		String url = "http://dbservice/getUser/"+email;		
		ResponseEntity<AuthenticationRequest> response = restTemplate.getForEntity(url, AuthenticationRequest.class);
		if(response.getStatusCodeValue() == 200) {
			AuthenticationRequest request = response.getBody();
			return new User(request.getEmail(),request.getPassword(),new ArrayList<>());
		}
		return null;
	}

}
