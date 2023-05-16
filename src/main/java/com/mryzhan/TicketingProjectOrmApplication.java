package com.mryzhan;

import com.mryzhan.repository.ProjectRepository;
import com.mryzhan.service.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TicketingProjectOrmApplication {


	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(TicketingProjectOrmApplication.class, args);


	}

	@Bean
	public ModelMapper mapper(){
		return new ModelMapper();
	}


}
