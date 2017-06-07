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
public class PersonServiceTest {
    
    @Autowired
    private PersonService personService;
    
    @Autowired
    private PersonRepository personRepository;
    
    @Autowired
    private TokenRepository tokenRepository;
    
    @Test
    public void deletePerson() throws Exception {
    
        Person person = personRepository.save(new Person("person"));
        tokenRepository.save(new Token(person, "token"));
        assertThat(tokenRepository.count(), is(1L));
    
        personService.delete(person);
        assertThat(tokenRepository.count(), is(0L));
    }
}