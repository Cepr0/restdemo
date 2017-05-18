package restsdemo.example1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * @author Cepro, 2016-12-25
 */
@RepositoryRestResource
public interface ParentRepo extends JpaRepository<Parent, Long> {

    @Query("select p.name as parentName, c.name as childName, r.description as description from Parent p join p.children c join c.reference r where p.id = ?1 order by r.description desc")
    List<DemoDto> getDto(Long parentId);
}
