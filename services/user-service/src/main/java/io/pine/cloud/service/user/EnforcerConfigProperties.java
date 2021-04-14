package io.pine.cloud.service.user;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * jcasbin配置
 *
 * @author frank.liu
 * @version 1.0
 * @since 2021/4/14
 */
@Configuration
@ConfigurationProperties(prefix = "casbin")
public class EnforcerConfigProperties {
    private String url;

    private String driverClassName;

    private String username;

    private String password;

    private String modelPath;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getModelPath() {
        return modelPath;
    }

    public void setModelPath(String modelPath) {
        this.modelPath = modelPath;
    }

    @Override
    public String toString() {
        return "EnforcerConfigProperties [url=" + url + ", driverClassName=" + driverClassName + ", username="
                + username + ", password=" + password + ", modelPath=" + modelPath + "]";
    }
}
