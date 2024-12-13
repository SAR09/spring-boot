package programmermuda.validation.properties;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties("database")
public class DatabaseProperties {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public DatabaseProperties(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public DatabaseProperties() {
    }

    public @NotBlank String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank String username) {
        this.username = username;
    }

    public @NotBlank String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank String password) {
        this.password = password;
    }
}
