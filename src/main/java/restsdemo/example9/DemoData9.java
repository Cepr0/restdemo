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
    private final @NonNull BiWorkerRepo biWorkerRepo;
    private final @NonNull BiPositionRepo biPositionRepo;

    @Async
    @EventListener
    @Transactional
    public void appReady(ApplicationReadyEvent event) {
    
        List<Position> positions = positionRepo.save(asList(
            new Position("position1"),
            new Position("position2"),
            new Position("position3"),
            new Position("position4"),
            new Position("position5"),
            new Position("position6"),
            new Position("position7"),
            new Position("position8"),
            new Position("position9"),
            new Position("position10")
        ));

        workerRepo.save(asList(
            new Worker("worker1", positions.get(0), positions.get(1)),
            new Worker("worker2", positions.get(0), positions.get(3)),
            new Worker("worker3", positions.get(4), positions.get(5)),
            new Worker("worker4", positions.get(1), positions.get(4), positions.get(0)),
            new Worker("worker5", positions.get(0), positions.get(1), positions.get(6))
        ));

        List<BiPosition> biPositions = biPositionRepo.save(asList(new BiPosition("position1"), new BiPosition("position2")));

        BiWorker biWorker1 = new BiWorker("worker1");
        biWorker1.getPositions().add(biPositions.get(0));

        BiWorker biWorker2 = new BiWorker("worker2");
        biWorker2.getPositions().addAll(asList(biPositions.get(0), biPositions.get(1)));

        biWorkerRepo.save(asList(biWorker1, biWorker2));

    }
}
