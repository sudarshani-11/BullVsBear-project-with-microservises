package com.bullvsbear.apigateway.controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bullvsbear.apigateway.exceptions.NoSuchUserFoundException;
import com.bullvsbear.apigateway.exceptions.ValidationError;
import com.bullvsbear.apigateway.models.AuthenticationRequest;
import com.bullvsbear.apigateway.models.AuthenticationResponse;
import com.bullvsbear.apigateway.models.RegistrationDetails;
import com.bullvsbear.apigateway.services.UsersDetailsService;
import com.bullvsbear.apigateway.utils.JwtUtil;

@RestController
@RequestMapping("/bullvsbear/api")
@CrossOrigin(origins = "http://localhost:4200")
public class GatewayController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UsersDetailsService usersDetailsService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@GetMapping("/test/{msg}")
	public String hello(@PathVariable(name="msg") String msg) {
		System.out.println("Request Received");
		return "hello "+msg;
	} 
	
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
							authenticationRequest.getPassword())
					);
		}catch(BadCredentialsException e) {
			return ResponseEntity.badRequest().body("Invalid Username or Password");
		}
		final UserDetails userDetails = usersDetailsService.loadUserByUsername(authenticationRequest.getEmail());
		if(userDetails == null) throw new NoSuchUserFoundException("No user is registered with email "+authenticationRequest.getEmail());
		final String jwt = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegistrationDetails registrationDetails){
		
		System.out.println(registrationDetails);
		String regex = "((?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_-])(?=.*[A-Z])(?=\\S+$).{8,16})";  //Regex for password validation 
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(registrationDetails.getPassword());
		if(!matcher.matches())
			throw new ValidationError("Attention!! Please check the password, it must be alphanumeric with atleast one special char(!,@,#,$,%,^,&,*,(,),_,-,) with min 8 chars and max 16 chars.");
		
		regex = "^[a-zA-Z]{1,20}$";  //Regex for last name validation 
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(registrationDetails.getFirstName());
		if(!matcher.matches())
			throw new ValidationError("First name can contains only alphabets , min 1 chars , max 20 chars.");
		
		regex = "^[a-zA-Z]{1,20}$";  //Regex for last name validation 
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(registrationDetails.getLastName());
		if(!matcher.matches())
			throw new ValidationError("Last name can contains only alphabets , min 1 chars , max 20 chars.");
		
		regex = "^[a-z0-9]{1,15}$";  //Regex for user name validation
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(registrationDetails.getUserName());
		if(!matcher.matches())
			throw new ValidationError("User name can contains only alphabets in lower case and numeric char, min 1 chars , max 15 chars.");
		
		regex = "^[a-z0-9+_.-]+@(.+)$";		//Regex for email validation
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(registrationDetails.getEmail());
		if(!matcher.matches())
			throw new ValidationError("Please provide proper email address. All alphabets must be in lowercase");
		
		String url = "http://dbservice/registerUser";
		registrationDetails.setPassword(passwordEncoder.encode(registrationDetails.getPassword()));
		String response = restTemplate.postForObject(url,registrationDetails,String.class);
		return ResponseEntity.status(201).body(response);
	}
}
