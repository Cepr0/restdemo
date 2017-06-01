package restsdemo.example5;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Arrays.asList;

/**
 *
 */
@Component
@RequiredArgsConstructor
public class DemoData5 {

    private final @NonNull QuestionRepository repository;

    @Async
    @EventListener
    @Transactional
    public void appReady(ApplicationReadyEvent event) {
        repository.save(asList(new Question("question1"), new Question("question2")));
    }
}
