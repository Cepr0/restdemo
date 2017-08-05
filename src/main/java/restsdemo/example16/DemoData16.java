package restsdemo.example16;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Cepro
 * @since 2017-08-03
 */
@Component
@RequiredArgsConstructor
public class DemoData16 {

    private final WayRepo wayRepo;
    
    @EventListener
    @Transactional
    public void appReady(ApplicationReadyEvent event) {
        wayRepo.save(new Way("way1"));
    }
}
