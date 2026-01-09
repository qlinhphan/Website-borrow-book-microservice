package com.example.userservices.command.controller;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.commonservices.service.SendMessageKafka;
import com.example.userservices.command.command.CommandCreateUser;
import com.example.userservices.command.command.CommandDeleteUser;
import com.example.userservices.command.command.CommandUpdateUser;
import com.example.userservices.command.model.ModelCreateUser;
import com.example.userservices.command.model.ModelDeleteUser;
import com.example.userservices.command.model.ModelUpdateUser;
import com.netflix.discovery.converters.Auto;

import jakarta.validation.Valid;

// them, cap nhat boi id, xoa boi id
@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private SendMessageKafka sendMessageKafka;

    @PostMapping
    public String createUser(@Valid @RequestBody ModelCreateUser modelCreateUser) {
        CommandCreateUser command = new CommandCreateUser();
        command.setId(UUID.randomUUID().toString());
        command.setFName(modelCreateUser.getFName());
        command.setLName(modelCreateUser.getLName());
        command.setKin(modelCreateUser.getKin());
        command.setDiscipline(false);
        this.commandGateway.sendAndWait(command);

        this.sendMessageKafka.sendMessage("you just have add a user");

        return "create user successfully";
    }

    @PutMapping("/{id}")
    public String updateUser(@RequestBody ModelUpdateUser model, @PathVariable("id") String id) {
        CommandUpdateUser command = new CommandUpdateUser();
        command.setId(id);
        command.setFName(model.getFName());
        command.setLName(model.getLName());
        command.setKin(model.getKin());
        command.setDiscipline(model.isDiscipline());

        this.commandGateway.sendAndWait(command);

        return "update user successfully";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String id) {
        CommandDeleteUser command = new CommandDeleteUser();
        command.setId(id);

        this.commandGateway.sendAndWait(command);

        return "deleted user who has id: " + id;
    }

}
