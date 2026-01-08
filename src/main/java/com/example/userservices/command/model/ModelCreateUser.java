package com.example.userservices.command.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModelCreateUser {
    private String id;
    private String fName;
    private String lName;
    @NotBlank(message = "everything you can fill with after, but you have to write kin")
    private String kin; // mnv
    private boolean discipline; // bi ky luat
}
