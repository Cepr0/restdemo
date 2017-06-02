package restsdemo.example7;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PreRemove;

/**
 *
 */
@NoArgsConstructor
@Component
public class PersonListener {
    
    // https://stackoverflow.com/a/12241344/5380322
    private static TokenRepository tokenRepository;
    
    @Autowired
    public void setTokenRepository(TokenRepository tokenRepository) {
        PersonListener.tokenRepository = tokenRepository;
    }
    
    @PreRemove
    void preRemove(Person person) {
        tokenRepository.deleteByPerson(person);
    }
}
