package gestion.scolaire.config;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.media.Schema;

public class LoginRequest {
   
    @Schema(required = true)
    private String login;

    @Schema(required = true)
    private String password;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login  = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}