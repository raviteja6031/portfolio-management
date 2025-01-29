package com.customer.portal.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.customer.portal.vo.*;

@FeignClient(url = "http://localhost:8081/users", name = "USER-CLIENT")
public interface UserAuthorizationClient {

	@PostMapping("/getToken")
	public ResponseEntity<String> createAuthenticationToken(@RequestBody JwtRequestVO authenticationRequest);
	
	@PostMapping("/getUid")
	public ResponseEntity<Integer> getById(@RequestHeader("Authorization") String authorization);
	
	@GetMapping("/getuname")
	public ResponseEntity<String> getUname(@RequestHeader("Authorization") String authorization);
	
	@GetMapping("/validate")
	public Boolean validateToken(@RequestHeader("Authorization") String authorization);
}
