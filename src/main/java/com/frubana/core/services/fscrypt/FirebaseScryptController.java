package com.frubana.core.services.fscrypt;

import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class FirebaseScryptController {
	
	FirebaseScryptService service;
	
	public FirebaseScryptController(FirebaseScryptService service) {
		this.service = service;
	}
	
	@PostMapping("/hash")
	public String hash(@RequestBody FScryptParametersPayload payload) {
		try {
			return service.hash(
					payload.getRaw(), 
					payload.getKey(), 
					payload.getSalt(), 
					payload.getSeparator(),
					payload.getRounds(),
					payload.getMemCost());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
