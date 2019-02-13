package com.mudndcapstone.server

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@SpringBootApplication
class ServerApplication {

	static void main(String[] args) {
		SpringApplication.run(ServerApplication, args)
	}

	@RequestMapping("/")
	String hello() {
		return "Hello Dungeoneers Again"
	}
}

