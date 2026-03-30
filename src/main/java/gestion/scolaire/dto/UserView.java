package gestion.scolaire.dto;

import gestion.scolaire.model.Role;

public interface UserView {

    Long getIdUser();

    String getUsername();

    String getTelephone();

    String getEmail();

    String getAdresse();

    String getDateEnregistrement();

    Role getRole();
}

