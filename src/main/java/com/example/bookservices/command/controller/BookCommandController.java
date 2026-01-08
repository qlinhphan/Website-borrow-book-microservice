package com.example.bookservices.command.controller;

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

import com.example.bookservices.command.command.CommandCreateBook;
import com.example.bookservices.command.command.CommandDeleteBook;
import com.example.bookservices.command.command.CommandUpdateBook;
import com.example.bookservices.command.model.ModelCreateBook;
import com.example.bookservices.command.model.ModelUpdateBook;

@RestController
@RequestMapping("/v1/books")
public class BookCommandController {

    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    public String postBook(@RequestBody ModelCreateBook model) {
        CommandCreateBook command = new CommandCreateBook();
        command.setId(UUID.randomUUID().toString());
        command.setName(model.getName());
        command.setAuthor(model.getAuthor());
        command.setReady(true);

        this.commandGateway.sendAndWait(command);

        return "create a book successfully";
    }

    @PutMapping("/{id}")
    public String updateBook(@PathVariable("id") String id, @RequestBody ModelUpdateBook model) {
        CommandUpdateBook command = new CommandUpdateBook();
        command.setId(id);
        command.setName(model.getName());
        command.setAuthor(model.getAuthor());
        command.setReady(model.isReady());

        this.commandGateway.sendAndWait(command);

        return "Update a book successfully";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") String id) {
        CommandDeleteBook command = new CommandDeleteBook();
        command.setId(id);

        this.commandGateway.sendAndWait(command);

        return "delete a book successfully";
    }

}
