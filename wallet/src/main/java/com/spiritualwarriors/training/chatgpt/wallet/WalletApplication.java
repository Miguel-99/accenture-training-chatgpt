package com.spiritualwarriors.training.chatgpt.wallet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallets")
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class WalletApplication {

	@Value("${server.port}")
	private String port;

	public static void main(String[] args) {
		SpringApplication.run(WalletApplication.class, args);
	}

	@GetMapping("/hello")
	public String wallet() {
		return "Hello From wallet app, port: " + port;
	}
}
