package atmApp.services;

import atmApp.domain.User;
import atmApp.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyUserDetailsServiceTest {

    @Autowired
    UserRepository repo;

    @Test
    @Transactional
    public void returnUserDetails() {
        long id = 1;
        User user  = repo.getById(id);
        System.out.println(user);
    }
}
