package com.bullvsbear.dbservice.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bullvsbear.dbservice.entity.AuthenticationResponse;
import com.bullvsbear.dbservice.entity.RegistrationDetails;
import com.bullvsbear.dbservice.entity.User;
import com.bullvsbear.dbservice.entity.UserQuote;
import com.bullvsbear.dbservice.exceptions.IllegalRegistrationException;
import com.bullvsbear.dbservice.exceptions.NoSuchUserFoundException;
import com.bullvsbear.dbservice.repo.UserQuoteRepository;
import com.bullvsbear.dbservice.repo.UserRepo;

@RestController
public class DbserviceController {

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	UserQuoteRepository userQuoteRepository;
	
	@PostMapping("/registerUser")
	public ResponseEntity<String> getUserRegister(@RequestBody RegistrationDetails registrationDetails){
		System.out.println(registrationDetails.getFirstName()+" : "+registrationDetails.getPassword());
		if(userRepo.existsByuserName(registrationDetails.getUserName()))
			throw new IllegalRegistrationException("Username already exist,Please select another username");
		
		if(userRepo.existsByEmail(registrationDetails.getEmail()))
			throw new IllegalRegistrationException("Email already registered. Try with different email id.");
		
		User user = new User(registrationDetails.getUserName(),registrationDetails.getFirstName(),registrationDetails.getLastName(),registrationDetails.getEmail(),registrationDetails.getPassword());
		user = userRepo.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body("User successfully registered with user id:- "+ user.getUserId());
	}
	
	
	// This method will be called by UserDetails Service in Apigateway for authentication
	@GetMapping("getUser/{email}")
	public ResponseEntity<AuthenticationResponse> getUserByEmail(@PathVariable String email){
		if(!userRepo.existsByEmail(email))
			throw new NoSuchUserFoundException("No user found with email:- "+email);
		User user = userRepo.findByEmail(email);
		AuthenticationResponse response  = new AuthenticationResponse(user.getEmail(),user.getPassword());
		return ResponseEntity.status(200).body(response);
	}
	

	@PutMapping("addUserQuote/{userId}")
	public ResponseEntity<?> addQuote(@RequestBody UserQuote userQuoteRequest, @PathVariable Long userId){
		Optional<User> user = userRepo.findById(userId);
		if(!user.isPresent()) throw new NoSuchUserFoundException("No user with id:- "+userId+" was found.");
		List<UserQuote> quoteList = user.get().getUserQuote();
		if(quoteList.contains(userQuoteRequest)) return ResponseEntity.status(204).body("User quote Already present.");
		userQuoteRequest.setUser(user.get());
		quoteList.add(userQuoteRequest);
		user.get().setUserQuote(quoteList);
		userRepo.save(user.get());
		return ResponseEntity.ok(userQuoteRequest.getLongName()+" added succesfully");
	}
	
	//Method for user details
	@GetMapping("getUserDetails/{email}")
	public ResponseEntity<User> getUserByEmailAngular(@PathVariable String email){
		if(!userRepo.existsByEmail(email))
			throw new NoSuchUserFoundException("No user found with email:- "+email);
		User user = userRepo.findByEmail(email);
		user.setPassword("   ");
		user.setUserQuote(null);
		return ResponseEntity.status(200).body(user);
	}
	
	
	//Method for user quotes based on user id 
	@GetMapping("getUserQuotes/{userId}")
	public ResponseEntity<?> getUserQuotes(@PathVariable Long userId){
		if(!userRepo.existsById(userId)) throw new NoSuchUserFoundException("No user with id:- "+userId+" was found.");
		List<UserQuote> userQuotes = userQuoteRepository.findByUserId(userId).stream().collect(Collectors.toList());
		return ResponseEntity.ok(userQuotes);
	}
}
