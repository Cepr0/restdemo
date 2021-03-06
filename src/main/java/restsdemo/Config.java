package restsdemo;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import restsdemo.example19.Branch;

import java.time.LocalDate;

/**
 * @author Cepro, 2016-12-24
 */
@Configuration
public class Config extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureConversionService(ConfigurableConversionService conversionService) {
        conversionService.addConverter(String.class, LocalDate.class, LocalDate::parse);
        super.configureConversionService(conversionService);
    }

    @Override // isn't invoked...
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Branch.class);
        super.configureRepositoryRestConfiguration(config);
    }

}
