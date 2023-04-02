package cc.sika.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author 吴畅
 * @创建时间 2023/2/2 - 21:04
 */
@ConfigurationProperties("sika.storage")
@Configuration
public class StorageProperties {
    private String location = "D:/sika/file/general/";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
