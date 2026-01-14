package com.example.bookservices.command.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.example.bookservices.command.command.CommandCreateBook;
import com.example.bookservices.command.command.CommandDeleteBook;
import com.example.bookservices.command.command.CommandUpdateBook;
import com.example.bookservices.command.event.EventCreateBook;
import com.example.bookservices.command.event.EventDeleteBook;
import com.example.bookservices.command.event.EventUpdateBook;
import com.example.commonservices.command.book.CommandUpdateSttBook;
import com.example.commonservices.command.book.EventUpdateSttBook;

import lombok.NoArgsConstructor;

@Aggregate
@NoArgsConstructor
public class BookAggregate {
    @AggregateIdentifier
    private String id;
    private String name;
    private String author;
    private boolean ready;

    @CommandHandler
    public BookAggregate(CommandCreateBook command) {
        EventCreateBook event = new EventCreateBook();
        event.setId(command.getId());
        event.setName(command.getName());
        event.setAuthor(command.getAuthor());
        event.setReady(command.isReady());

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void handle(EventCreateBook event) {
        this.id = event.getId();
        this.name = event.getName();
        this.author = event.getAuthor();
        this.ready = event.isReady();
    }

    @CommandHandler
    public void handle(CommandUpdateBook command) {
        EventUpdateBook event = new EventUpdateBook();
        event.setId(command.getId());
        event.setName(command.getName());
        event.setAuthor(command.getAuthor());
        event.setReady(command.isReady());

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void ok(EventUpdateBook event) {
        this.id = event.getId();
        this.name = event.getName();
        this.author = event.getAuthor();
        this.ready = event.isReady();
    }

    @CommandHandler
    public void deleteCH(CommandDeleteBook command) {
        EventDeleteBook event = new EventDeleteBook();
        event.setId(command.getId());

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void eventSDele(EventDeleteBook event) {
        this.id = event.getId();
        this.author = null;
        this.name = null;
        this.ready = false;
    }

    @CommandHandler
    public void handler(CommandUpdateSttBook command) {
        EventUpdateSttBook event = new EventUpdateSttBook();
        event.setId(command.getId());
        event.setAuthor(command.getAuthor());
        event.setName(command.getName());
        event.setReady(command.isReady());

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void esh(EventUpdateSttBook event) {
        this.id = event.getId();
        this.author = event.getAuthor();
        this.name = event.getName();
        this.ready = event.isReady();
    }

}
