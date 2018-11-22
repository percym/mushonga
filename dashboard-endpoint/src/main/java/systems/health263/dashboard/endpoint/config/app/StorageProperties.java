package systems.health263.dashboard.endpoint.config.app;

import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private Path location = Paths.get(System.getProperty("user.home")+"/dovaLogos") ;

    public Path getLocation() {
        return location;
    }

    public void setLocation(Path location) {
        this.location = location;
    }

}
