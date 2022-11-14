package com.devsuperior.workshopmongo.dto;

import com.devsuperior.workshopmongo.entities.User;

public class UserDTO {
    private String id;
    private String name;
    private String email;

    public UserDTO() {
    }

    public UserDTO(User userEntity) {
        id = userEntity.getId();
        name = userEntity.getName();
        email = userEntity.getEmail();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
