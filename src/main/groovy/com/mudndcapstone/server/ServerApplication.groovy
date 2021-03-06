package com.mudndcapstone.server

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectWriter
import org.modelmapper.ModelMapper
import org.modelmapper.jackson.JsonNodeValueReader
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories
import org.springframework.web.bind.annotation.RestController

@RestController
@EnableNeo4jRepositories
@SpringBootApplication
class ServerApplication {

	static void main(String[] args) {
		SpringApplication.run(ServerApplication, args)
	}

	@Bean
	static ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper()
		modelMapper.getConfiguration().setAmbiguityIgnored(true)
		modelMapper.getConfiguration().addValueReader(new JsonNodeValueReader())
		modelMapper
	}

}

