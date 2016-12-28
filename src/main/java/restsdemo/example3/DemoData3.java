package restsdemo.example3;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Cepro, 2016-12-28
 */
@Service
@RequiredArgsConstructor
public class DemoData3 {

    private final BookCategoryRepository bookCategoryRepository;
    
    @Async
    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        BookCategory categoryA = new BookCategory("Category A");
        Set bookAs = new HashSet<Book>(){{
            add(new Book("Book A1", categoryA));
            add(new Book("Book A2", categoryA));
            add(new Book("Book A3", categoryA));
        }};
        categoryA.setBooks(bookAs);
    
        BookCategory categoryB = new BookCategory("Category B");
        Set bookBs = new HashSet<Book>(){{
            add(new Book("Book B1", categoryB));
            add(new Book("Book B2", categoryB));
            add(new Book("Book B3", categoryB));
        }};
        categoryB.setBooks(bookBs);
        
        bookCategoryRepository.save(categoryA);
        bookCategoryRepository.save(categoryB);
    }
}
