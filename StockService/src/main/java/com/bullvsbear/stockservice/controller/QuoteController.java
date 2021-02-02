package com.bullvsbear.stockservice.controller;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class QuoteController {
	
	private final String apiKey = "60918a34c7msh0dd9fe4ea15ed4fp13889cjsnd8138a84d936"; 
	private final String host = "apidojo-yahoo-finance-v1.p.rapidapi.com";
	
	@Autowired
	private RestTemplate restTemplate ;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/search/{stock}" ) 
	public ResponseEntity<String> search(@PathVariable(name="stock") String stock){ 
		
		final String URI = "https://apidojo-yahoo-finance-v1.p.rapidapi.com/auto-complete?q=" +
				  							  stock;
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		httpHeaders.set("x-rapidapi-key",apiKey);
		httpHeaders.set("x-rapidapi-host",host);
	  
		HttpEntity <String> httpEntity = new HttpEntity<String>(httpHeaders);
	  
		ResponseEntity<String> responseEntity =
				restTemplate.exchange(URI,HttpMethod.GET,httpEntity,String.class);
		
		httpHeaders = HttpHeaders.writableHttpHeaders(responseEntity.getHeaders());
		httpHeaders.setAccessControlAllowOrigin(null);
		return ResponseEntity.ok().headers(httpHeaders).body(responseEntity.getBody()); 
	 }
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/quote/{stock}")
	public ResponseEntity<String> quote(@PathVariable(name="stock") String stock){
		final String URI = "https://apidojo-yahoo-finance-v1.p.rapidapi.com/market/v2/get-quotes?region=US&symbols=" +stock;                                           //Need Edit
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		httpHeaders.set("x-rapidapi-key",apiKey);
		httpHeaders.set("x-rapidapi-host",host);
	  
		HttpEntity <String> httpEntity = new HttpEntity<String>(httpHeaders);
	  
		ResponseEntity<String> responseEntity =
				restTemplate.exchange(URI,HttpMethod.GET,httpEntity,String.class);
		
		httpHeaders = HttpHeaders.writableHttpHeaders(responseEntity.getHeaders());
		httpHeaders.setAccessControlAllowOrigin(null);
		return ResponseEntity.ok().headers(httpHeaders).body(responseEntity.getBody()); 
		
	}
	@GetMapping("/history/{stock}")
	public ResponseEntity<String> history(@PathVariable(name="stock") String stock){
		final String URI = "https://apidojo-yahoo-finance-v1.p.rapidapi.com//stock/v3/get-historical-data?symbol=AMRN&region=US" +stock;                                           //Need Edit
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		httpHeaders.set("x-rapidapi-key",apiKey);
		httpHeaders.set("x-rapidapi-host",host);
	  
		HttpEntity <String> httpEntity = new HttpEntity<String>(httpHeaders);
	  
		ResponseEntity<String> responseEntity =
				restTemplate.exchange(URI,HttpMethod.GET,httpEntity,String.class);
		
		httpHeaders = HttpHeaders.writableHttpHeaders(responseEntity.getHeaders());
		httpHeaders.setAccessControlAllowOrigin(null);
		return ResponseEntity.ok().headers(httpHeaders).body(responseEntity.getBody()); 
		
	}
	  
}
