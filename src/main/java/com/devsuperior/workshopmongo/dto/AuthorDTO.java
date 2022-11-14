package com.devsuperior.workshopmongo.dto;

import com.devsuperior.workshopmongo.entities.User;

public class AuthorDTO {
    private String id;
    private String name;

    public AuthorDTO() {
    }

    public AuthorDTO(User userEntity) {
        id = userEntity.getId();
        name = userEntity.getName();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
