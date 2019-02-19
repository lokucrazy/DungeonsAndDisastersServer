package com.mudndcapstone.server

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories
import org.springframework.web.bind.annotation.RestController

@RestController
@EnableNeo4jRepositories
@SpringBootApplication
class ServerApplication {

	static void main(String[] args) {
		SpringApplication.run(ServerApplication, args)
	}

}

