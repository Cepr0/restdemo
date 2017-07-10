package restsdemo.example10;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Cepro
 * @since 2017-07-07
 */
public interface VehicleRepo extends JpaRepository<Vehicle, Long> {

    @Query("select v.tires from Vehicle v where v.gallons > ?1")
    List<Tire> findByGallonsIsGreaterThan(int gallons);
}
