package restsdemo.example6;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;
    
    @Test
    public void getUsers() throws Exception {
        List<User> users = repository.getUsers(User.Role.ROLE2, Orderr.State.STATE1);
        users.stream().map(User::getName).forEach(System.out::println);
    
        users = repository.getUsers(User.Role.ROLE1, Orderr.State.STATE2);
        users.stream().map(User::getName).forEach(System.out::println);

    }
}