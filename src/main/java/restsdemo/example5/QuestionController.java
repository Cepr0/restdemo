package restsdemo.example5;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 */
@RequiredArgsConstructor
@BasePathAwareController
@RequestMapping("/questions")
public class QuestionController {

    private final @NonNull QuestionRepository repository;
    private final @NonNull PagedResourcesAssembler<Question> assembler;
    private final @NonNull EntityLinks links;

    @GetMapping
    ResponseEntity<?> get(Pageable pageable) {
        return ResponseEntity.ok(assembler.toResource(repository.findAll(pageable),
                (ResourceAssembler<Question, ResourceSupport>) question ->
                        new Resource<>(question,
                        links.linkToSingleResource(question).withSelfRel())));
    }
}
