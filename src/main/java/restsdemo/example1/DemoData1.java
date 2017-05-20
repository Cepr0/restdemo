package restsdemo.example1;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Arrays.asList;

/**
 * @author Cepro, 2016-12-24
 */
@Service
@RequiredArgsConstructor
public class DemoData1 {
    
    private final @NonNull ToyRepo toyRepo;
    
    private final @NonNull ReferenceRepo referenceRepo;
    
    private final @NonNull ChildRepo childRepo;
    
    private final @NonNull ParentRepo parentRepo;
    
    @Async
    @EventListener
    @Transactional
    public void appReady(ApplicationReadyEvent event) {
        
        Reference reference1 = new Reference("Description1");
        Reference reference2 = new Reference("Description2");
        
        referenceRepo.save(asList(reference1, reference2));
        
        Child child1 = new Child("child1", reference1, new Toy("Toy1"), new Toy("Toy2"), new Toy("Toy3"));
        Child child2 = new Child("child2", reference2, new Toy("Toy4"), new Toy("Toy5"), new Toy("Toy6"));
        Child child3 = new Child("child3", reference1, new Toy("Toy7"), new Toy("Toy8"), new Toy("Toy9"));
        Child child4 = new Child("child4", reference2, new Toy("Toy10"), new Toy("Toy11"), new Toy("Toy12"));
        
        Parent parent1 = new Parent("parent1", child1, child2);
        Parent parent2 = new Parent("parent2", child3, child4);
        parentRepo.save(asList(parent1, parent2));
    }
}
