package restsdemo.h2;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.h2.tools.RunScript;
import org.h2.tools.Server;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import java.nio.charset.Charset;
import java.sql.SQLException;

/**
 *
 */
@Slf4j
@RequiredArgsConstructor
@BasePathAwareController
public class H2Controller {

    private final @NonNull Server server;
    
    @PostMapping("/backup")
    public ResponseEntity<?> backupH2() {
    
        try {
            RunScript.execute("jdbc:h2:./db/data", "sa", "", "./db/backup.sql", Charset.defaultCharset(), true);
            LOG.info("H2 is backed up.");
        } catch (SQLException e) {
            LOG.info("Cannot backup H2. Cause: {}", e.getMessage());
        }

        String status = server.getStatus();
        return ResponseEntity.ok(status);
    }
}
