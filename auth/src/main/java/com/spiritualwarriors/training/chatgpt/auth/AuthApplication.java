package com.spiritualwarriors.training.chatgpt.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

@RestController
@EnableDiscoveryClient
@SpringBootApplication
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

	@PostMapping("/auth")
	public ResponseEntity<String> auth(@RequestBody User user) {
		if ("ADMIN".equals(user.username) && "ADMIN".equals(user.password)) {
			String adminAuth = Base64.getEncoder().encodeToString("""
					{"user": "ADMIN", "userId": 1}
					""".getBytes());
			return ResponseEntity.ok()
					.header("Authorization", "Bearer a."+adminAuth+".c").body(adminAuth);

		} else if ("USER".equals(user.username) && "USER".equals(user.password)) {
			String userAuth = Base64.getEncoder().encodeToString("""
					{"user": "USER", "userId": 2}
					""".getBytes());
			return ResponseEntity.ok()
					.header("Authorization", "Bearer a."+userAuth+".c").body(userAuth);
		}

		return ResponseEntity.status(404).body("User not found");
	}

	record User (String username, String password) {}
}
