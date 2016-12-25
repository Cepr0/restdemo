package restsdemo;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import restsdemo.domain.entity.Child;
import restsdemo.domain.entity.Parent;
import restsdemo.domain.entity.Reference;
import restsdemo.repository.ChildRepo;
import restsdemo.repository.ParentRepo;
import restsdemo.repository.ReferenceRepo;

import java.util.Arrays;

import static java.util.Arrays.asList;

/**
 * @author Cepro, 2016-12-24
 */
@Service
@RequiredArgsConstructor
public class StartUp {
    
    private final @NonNull ReferenceRepo referenceRepo;
    
    private final @NonNull ChildRepo childRepo;
    
    private final @NonNull ParentRepo parentRepo;
    
    @Async
    @EventListener
    public void appReady(ApplicationReadyEvent event) {
    
        Reference reference1 = new Reference("Description1");
        Reference reference2 = new Reference("Description2");
    
        referenceRepo.save(asList(reference1, reference2));
        referenceRepo.flush();
    
        Child child1 = new Child("child1", reference1);
        Child child2 = new Child("child2", reference2);
        Child child3 = new Child("child3", reference1);
        Child child4 = new Child("child4", reference2);
    
        //        Child child1 = new Child("child1");
        //        Child child2 = new Child("child2");
        //        Child child3 = new Child("child3");
        //        Child child4 = new Child("child4");
    
        childRepo.save(Arrays.asList(child1, child2, child3, child4));
        childRepo.flush();
    
        Parent parent = new Parent("parent", child1, child2);
        parentRepo.save(parent);
    }
}
