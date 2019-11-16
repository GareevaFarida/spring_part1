package ru.geekbrains.controller.repr;

import javax.validation.constraints.NotEmpty;

public class AuthorityRepr {

    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private Long idUser;

    public AuthorityRepr() {
    }

     public Long getId() {
        return id;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
