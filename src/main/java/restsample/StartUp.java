package restsample;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import restsample.domain.entity.Child;
import restsample.domain.entity.Parent;
import restsample.domain.entity.Reference;
import restsample.repository.ChildRepo;
import restsample.repository.ParentRepo;
import restsample.repository.ReferenceRepo;

import java.util.Arrays;

import static java.util.Arrays.asList;

/**
 * @author Cepro, 2016-12-24
 */
@Service
@Slf4j
@AllArgsConstructor
public class StartUp {
 
    private final ReferenceRepo referenceRepo;
    
    private final ChildRepo childRepo;
    
    private final ParentRepo parentRepo;
    
    @Async
    @EventListener
    public void appReady(ApplicationReadyEvent event) {
    
        Reference reference1 = new Reference("Description1");
        Reference reference2 = new Reference("Description2");
    
        referenceRepo.save(asList(reference1, reference2));
    
//        Child child1 = new Child("child1", reference1);
//        Child child2 = new Child("child2", reference2);
//        Child child3 = new Child("child3", reference1);
//        Child child4 = new Child("child4", reference2);
    
        Child child1 = new Child("child1");
        Child child2 = new Child("child2");
        Child child3 = new Child("child3");
        Child child4 = new Child("child4");

        childRepo.save(Arrays.asList(child1, child2, child3, child4));
        childRepo.flush();
    
        Parent parent = new Parent("parent", child1, child2);
        parentRepo.save(parent);
    }
}
