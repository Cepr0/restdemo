package restsdemo.example6;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Arrays.asList;
import static restsdemo.example6.User.Role.ROLE1;
import static restsdemo.example6.User.Role.ROLE2;

/**
 *
 */
@Component
@RequiredArgsConstructor
public class DemoData6 {
    
    private final @NonNull UserRepository userRepository;
    private final @NonNull OrderRepository orderRepository;
    
    @Async
    @EventListener
    @Transactional
    public void appReady(ApplicationReadyEvent event) {
        
        User user1 = new User("user1", ROLE1);
        User user2 = new User("user2", ROLE2);
        
        userRepository.save(asList(user1, user2));
        
        orderRepository.save(asList(
                new Orderr("order1", user1, Orderr.State.STATE2),
                new Orderr("order2", user1, Orderr.State.STATE2),
                new Orderr("order3", user2, null)));
    }
}
