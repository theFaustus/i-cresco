package com.evil.inc.icresco.repo;

import com.evil.inc.icresco.domain.entity.Authority;
import com.evil.inc.icresco.domain.entity.Gender;
import com.evil.inc.icresco.domain.entity.User;
import com.evil.inc.icresco.domain.entity.UserAuthority;
import com.evil.inc.icresco.domain.exception.NotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class UserRepositoryIntegrationTest {
    @Container
    public static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>(DockerImageName.parse("postgres:13.3"));

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
    }

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        final User user = User.builder()
                .email("btest@gmail.com")
                .firstName("bob")
                .lastName("test")
                .gender(Gender.MALE)
                .username("btest")
                .enabled(true)
                .password("nino")
                .build();
        user.addAuthority(new UserAuthority(Authority.POWER_USER));

        userRepository.save(user);    }

    @Test
    public void findByUsername_whenUserExists_returnsUser() {
        User actualUser = userRepository.findByUsername("btest")
                .orElseThrow(() -> new NotFoundException("User not found"));

        assertThat(actualUser.getUsername()).isEqualTo("btest");
        assertThat(actualUser.getId()).isNotNull();
    }
}