package com.example.bookservices.command.event;

import java.util.Optional;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bookservices.command.command.CommandUpdateBook;
import com.example.bookservices.command.data.Book;
import com.example.bookservices.command.data.BookRepository;
import com.example.commonservices.command.book.EventUpdateSttBook;

@Component
public class EventHandling {

    @Autowired
    private BookRepository bookRepository;

    @EventHandler
    public void handlers(EventCreateBook event) {
        Book book = new Book();
        book.setId(event.getId());
        book.setName(event.getName());
        book.setAuthor(event.getAuthor());
        book.setReady(event.isReady());

        this.bookRepository.save(book);
    }

    @EventHandler
    public void update(EventUpdateBook event) {
        Optional<Book> bookFindById = this.bookRepository.findById(event.getId());

        bookFindById.ifPresent(x -> {
            x.setName(event.getName());
            x.setAuthor(event.getAuthor());
            x.setReady(event.isReady());

            this.bookRepository.save(x);
        });
    }

    @EventHandler
    public void handler(EventDeleteBook event) {
        Optional<Book> bookFindById = this.bookRepository.findById(event.getId());
        bookFindById.ifPresent(x -> {
            this.bookRepository.delete(x);
        });
    }

    @EventHandler
    public void handle(EventUpdateSttBook event) {
        Optional<Book> bookFindById = this.bookRepository.findById(event.getId());
        bookFindById.ifPresent(x -> {
            x.setId(event.getId());
            x.setReady(event.isReady());

            this.bookRepository.save(x);
        });
    }

}
