package com.example.userservices.command.event;

import java.util.Optional;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.userservices.command.data.User;
import com.example.userservices.command.data.UserRepository;

@Component
public class EventHandling {

    @Autowired
    private UserRepository userRepository;

    @EventHandler
    public void handle(EventCreateUser event) {
        User user = new User();
        user.setId(event.getId());
        user.setFName(event.getFName());
        user.setLName(event.getLName());
        user.setKin(event.getKin());
        user.setDiscipline(event.isDiscipline());

        this.userRepository.save(user);
    }

    @EventHandler
    public void handle(EventUpdateUser event) {
        Optional<User> user = this.userRepository.findById(event.getId());
        user.ifPresent(x -> {
            x.setFName(event.getFName());
            x.setLName(event.getLName());
            x.setKin(event.getKin());
            x.setDiscipline(event.isDiscipline());

            this.userRepository.save(x);
        });
    }

    @EventHandler
    public void Handle(EventDeleteUser event) {
        Optional<User> user = this.userRepository.findById(event.getId());
        user.ifPresent(x -> {
            this.userRepository.delete(x);
        });
    }
}
