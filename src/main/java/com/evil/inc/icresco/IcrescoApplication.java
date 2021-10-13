package com.evil.inc.icresco;

import com.evil.inc.icresco.domain.entity.Authority;
import com.evil.inc.icresco.domain.entity.Gender;
import com.evil.inc.icresco.domain.entity.User;
import com.evil.inc.icresco.domain.entity.UserAuthority;
import com.evil.inc.icresco.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Set;

@Slf4j
@EnableJpaAuditing
@EnableConfigurationProperties
@EnableAspectJAutoProxy
@EntityScan(basePackages = {"com.evil.inc.icresco.domain.entity"})
@SpringBootApplication
public class IcrescoApplication {

    public static void main(String[] args) {
        SpringApplication.run(IcrescoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            final User user = User.builder()
                    .email("bdylan@gmail.com")
                    .firstName("bob")
                    .lastName("dylan")
                    .gender(Gender.MALE)
                    .username("bdylan")
                    .enabled(true)
                    .password("nino")
                    .build();
            user.addAuthority(new UserAuthority(Authority.POWER_USER));

            userRepository.save(user);
            log.info("Found {}", userRepository.findByUsername("bdylan").get());
        };
    }
}
