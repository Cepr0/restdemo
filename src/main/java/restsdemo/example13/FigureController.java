package restsdemo.example13;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restsdemo.RestDemo;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

/**
 * See {@link RestDemo#errorAttributes()} method that make custom {@link ErrorAttributes}
 * @author Cepro
 *         2017-07-11
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@ControllerAdvice(basePackageClasses = {FigureController.class})
@RequestMapping("/figures")
public class FigureController {

    private final Figure.Repo repo;

    @GetMapping
    public ResponseEntity<?> get() {
        List<Figure> figures = repo.findAll();
        return ok(figures);
    }

    @PostMapping
    public ResponseEntity<?> post(@Valid @RequestBody Figure figure) {
        return ok(new Resource<>(repo.save(figure)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Figure figure,  @Valid @RequestBody Figure figureUpdated) {
        BeanUtils.copyProperties(figureUpdated, figure, "id");
        // figure.setHeight(figureUpdated.getHeight());
        // figure.setWidth(figureUpdated.getWidth());
        return ok(repo.save(figure));
    }
    
    @ExceptionHandler
    public void exceptionHandler(Exception e) throws Exception {
        LOG.error(e.getMessage());
        throw e;
    }
}
