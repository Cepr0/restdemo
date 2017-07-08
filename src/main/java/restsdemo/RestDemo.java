package restsdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.sql.SQLException;

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
}
