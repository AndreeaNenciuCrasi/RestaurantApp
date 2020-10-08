package com.codecool.restaurant;

import com.codecool.restaurant.User.UserApp;
import com.codecool.restaurant.User.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;


    @Test
    public void whenFindByUsername_thenReturnUserApp(){
        //given
        UserApp ion = new UserApp("Ion", "Ioana",
                "ion", "admin@gmail.com",
                "", "", "parola");
        entityManager.persist(ion);
        entityManager.flush();
        //when
        Optional<UserApp> found = userRepository.findByUserName(ion.getUserName());
        //then
        assertThat(found.get().getUserName()).isEqualTo(ion.getUserName());
    }
}
