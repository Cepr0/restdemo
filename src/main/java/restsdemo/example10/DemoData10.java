package restsdemo.example10;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Arrays.asList;

/**
 * @author Cepro
 * @since 2017-07-07
 */
@Component
@RequiredArgsConstructor
public class DemoData10 {
    
    private final VehicleRepo vehicleRepo;
    private final TireRepo tireRepo;
    
    @EventListener
    @Transactional
    public void appReady(ApplicationReadyEvent event) {
    
        List<Tire> tireList = tireRepo.save(asList(
                new Tire("tire1"),
                new Tire("tire2"),
                new Tire("tire3"),
                new Tire("tire4"),
                new Tire("tire5")
        ));
    
        vehicleRepo.save(asList(
                new Tank("tank1", 110, tireList.get(0), tireList.get(1)),
                new Tank("tank2", 90, tireList.get(2), tireList.get(3)),
                new Motorcycle("moto1", tireList.get(4))
        ));
    }
}
