package restsdemo.example7;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PersonListenerTest {
    
    @Autowired
    private PersonRepository personRepository;
    
    @Autowired
    private TokenRepository tokenRepository;
    
    @Test
    public void preRemove() throws Exception {
    
        Person person = personRepository.save(new Person("person1"));
        tokenRepository.save(new Token(person, "token1"));
        
        assertThat(tokenRepository.count(), is(1L));
        
        personRepository.delete(person);
        assertThat(tokenRepository.count(), is(0L));
    }
}