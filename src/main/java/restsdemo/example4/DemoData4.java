package restsdemo.example4;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Arrays.asList;

/**
 *
 */
@Service
@RequiredArgsConstructor
public class DemoData4 {

    private final CriterionRepository repository;

    @Async
    @EventListener
    @Transactional
    public void appReady(ApplicationReadyEvent event) {

        repository.save(asList(new NameCriterion("name1"), new NameCriterion("name2")));
        repository.save(asList(new TitleCriterion("title1"), new TitleCriterion("title2")));
    }
}
