package restsdemo.example9;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Arrays.asList;

/**
 *
 */
@Component
@RequiredArgsConstructor
public class DemoData9 {
    
    private final @NonNull WorkerRepo workerRepo;
    private final @NonNull PositionRepo positionRepo;
    
    @Async
    @EventListener
    @Transactional
    public void appReady(ApplicationReadyEvent event) {
    
        List<Position> positions = positionRepo.save(asList(new Position("position1"), new Position("position2")));
    
        Worker worker1 = new Worker("worker1");
        worker1.getPositions().add(positions.get(0));
        
        Worker worker2 = new Worker("worker2");
        worker2.getPositions().addAll(asList(positions.get(0), positions.get(1)));
    
        workerRepo.save(asList(
                // new Worker("worker1").addPositions(positions.get(0)),
                // new Worker("worker2").addPositions(positions.get(0), positions.get(1))
                worker1, worker2
        ));
    }
}
