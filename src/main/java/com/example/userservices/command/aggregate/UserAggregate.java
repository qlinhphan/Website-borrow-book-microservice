package com.example.userservices.command.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.example.userservices.command.command.CommandCreateUser;
import com.example.userservices.command.command.CommandDeleteUser;
import com.example.userservices.command.command.CommandUpdateUser;
import com.example.userservices.command.event.EventCreateUser;
import com.example.userservices.command.event.EventDeleteUser;
import com.example.userservices.command.event.EventUpdateUser;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Aggregate
public class UserAggregate {
    @AggregateIdentifier
    private String id;
    private String fName;
    private String lName;
    private String kin; // mnv
    private boolean discipline; // bi ky luat

    @CommandHandler
    public UserAggregate(CommandCreateUser commandCreateUser) {
        EventCreateUser event = new EventCreateUser();
        event.setId(commandCreateUser.getId());
        event.setFName(commandCreateUser.getFName());
        event.setLName(commandCreateUser.getLName());
        event.setKin(commandCreateUser.getKin());
        event.setDiscipline(commandCreateUser.isDiscipline());

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(EventCreateUser event) {
        this.id = event.getId();
        this.fName = event.getFName();
        this.lName = event.getLName();
        this.kin = event.getKin();
        this.discipline = event.isDiscipline();
    }

    @CommandHandler
    public void command(CommandUpdateUser command) {
        EventUpdateUser event = new EventUpdateUser();
        event.setId(command.getId());
        event.setFName(command.getFName());
        event.setLName(command.getLName());
        event.setKin(command.getKin());
        event.setDiscipline(command.isDiscipline());

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void handle(EventUpdateUser event) {
        this.id = event.getId();
        this.fName = event.getFName();
        this.lName = event.getLName();
        this.kin = event.getKin();
        this.discipline = event.isDiscipline();
    }

    @CommandHandler
    public void command(CommandDeleteUser command) {
        EventDeleteUser event = new EventDeleteUser();
        event.setId(command.getId());

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void handle(EventDeleteUser event) {
        this.id = event.getId();
        this.fName = null;
        this.lName = null;
        this.kin = null;
        this.discipline = false;
    }

}
