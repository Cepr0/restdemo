package restsdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.RequestAttributes;
import restsdemo.example19.Branch;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

/**
 * @author Cepro, 2016-12-24
 */
@SpringBootApplication
@EnableJpaRepositories(considerNestedRepositories = true)
// @EnableAsync
public class RestDemo {

    private static ObjectMapper OBJECT_MAPPER;

    @Autowired
    public RestDemo(ObjectMapper mapper) {
        OBJECT_MAPPER = mapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(RestDemo.class, args);
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2Server() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
    }

    @Bean
    public ErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes() {
            @Override
            public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
                Map<String, Object> errorAttributes = super.getErrorAttributes(requestAttributes, includeStackTrace);

                // If "errors" attribute is List of FieldError's
                // replace them with simple Map of field name and its error message
                Object attribute = errorAttributes.get("errors");
                if (attribute instanceof List) {

                    List list = (List) attribute;
                    if (!list.isEmpty() && list.get(0) instanceof FieldError) {

                        @SuppressWarnings("unchecked")
                        List<FieldError> errors = (List<FieldError>) attribute;

                        Map<String, String> messages = errors
                                .stream()
                                .collect(toMap(FieldError::getField, FieldError::getDefaultMessage));

                        errorAttributes.put("errors", messages);
                    }
                }
                return errorAttributes;
            }
        };
    }

    @Bean
    public RepositoryRestConfigurerAdapter repositoryRestConfigurerAdapter() {
        return new RepositoryRestConfigurerAdapter() {
            @Override
            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
                config.exposeIdsFor(Branch.class);
                super.configureRepositoryRestConfiguration(config);
            }
        };
    }
}
