package com.evil.inc.icresco.repo;

import com.evil.inc.icresco.common.AbstractIntegrationTest;
import com.evil.inc.icresco.domain.entity.User;
import com.evil.inc.icresco.domain.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findAll_whenThereAreUsers_returnsUsers() {
        final List<User> all = userRepository.findAll();
        assertThat(all).hasSize(8);
    }

    @Test
    @Sql(scripts = "/data-jpa-test-data/user-repository/users.sql")
    public void findByUsername_whenUserExists_returnsUser() {
        User actualUser = userRepository.findByUsername("etestley")
                .orElseThrow(() -> new NotFoundException("User not found"));

        assertThat(actualUser.getUsername()).isEqualTo("etestley");
        assertThat(actualUser.getId()).isNotNull();
    }
}
