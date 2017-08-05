package restsdemo.example15;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Cepro
 * @since 2017-08-05
 */
public interface MasterDto {
    
    Long getId();
    
    String getName();
    
    @Value("#{target.slaves}")
    List<Slave> getSlaves();
    
    @JsonProperty("")
    default List<String> getSlaveNames() {
        return getSlaves().stream().map(Slave::getName).collect(Collectors.toList());
    }
}
