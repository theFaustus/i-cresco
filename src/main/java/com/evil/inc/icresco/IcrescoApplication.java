package com.evil.inc.icresco;

import com.evil.inc.icresco.model.User;
import com.evil.inc.icresco.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties
@EnableAspectJAutoProxy
@Slf4j
public class IcrescoApplication {

	public static void main(String[] args) {
		SpringApplication.run(IcrescoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(UserRepository userRepository){
		return args -> {
			userRepository.save(new User("Bob", "Dylan", "bdylan", true, "dylan", "bdylan@gmail.com"));
			log.info("Found {}", userRepository.findByUserName("bdylan").get());
		};
	}
}
