package restsdemo.example16;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Cepro
 * @since 2017-08-03
 */
@RequiredArgsConstructor
@Component
public class ManService {
    
    private final @NonNull ManRepo manRepo;
    
    @Transactional
    public Man save(Man man) {
        return manRepo.save(man);
    }
    
    @Transactional(readOnly = true)
    public Man get(Long id) {
        return manRepo.fetchOne(id);
    }
}
