package restsdemo.example7;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@RequiredArgsConstructor
@Service
public class PersonService {
    
    private final PersonRepository personRepository;
    private final TokenRepository tokenRepository;
    
    @Transactional
    public void delete(Person person) {
    
        tokenRepository.deleteByPerson(person);
        personRepository.delete(person);
    }
}
