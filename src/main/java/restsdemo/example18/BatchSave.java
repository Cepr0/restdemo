package restsdemo.example18;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cepro on 05.09.2017.
 */
@Transactional
@Component
public class BatchSave {

    @PersistenceContext
    private EntityManager em;

	@Transactional
    public List<Sample> process(List<Sample> batch) {

        int size = batch.size();
        List<Sample> savedEntities = new ArrayList<>(size);

        for (Sample t : batch) {
            Sample result;
            if (t.getId() == null) {
                em.persist(t);
                result = t;
            } else {
                result = em.merge(t);
            }
            savedEntities.add(result);
        }
        em.flush();
        em.clear();
        return savedEntities;
    }
}
