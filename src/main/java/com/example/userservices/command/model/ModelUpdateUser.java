package com.example.userservices.command.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModelUpdateUser {
    private String id;
    private String fName;
    private String lName;
    private String kin; // mnv
    private boolean discipline; // bi ky luat
}
