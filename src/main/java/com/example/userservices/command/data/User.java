package com.example.userservices.command.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    private String id;
    private String fName;
    private String lName;

    private String kin; // mnv
    private boolean discipline; // bi ky luat
}
