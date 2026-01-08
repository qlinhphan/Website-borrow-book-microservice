package com.example.bookservices.query.projection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bookservices.command.data.Book;
import com.example.bookservices.command.data.BookRepository;
import com.example.bookservices.query.model.BookModel;
import com.example.bookservices.query.queries.ById;
import com.example.bookservices.query.queries.GetAll;

@Component
public class BookProjection {

    @Autowired
    private BookRepository bookRepository;

    @QueryHandler
    public List<BookModel> finds(GetAll queries) {
        List<Book> findAlls = this.bookRepository.findAll();

        List<BookModel> bms = new ArrayList<>();
        findAlls.forEach(x -> {
            BookModel bm = new BookModel();
            bm.setId(x.getId());
            bm.setName(x.getName());
            bm.setAuthor(x.getAuthor());
            bm.setReady(x.isReady());

            bms.add(bm);
        });
        return bms;
    }

    @QueryHandler
    public BookModel findd(ById query) {
        Optional<Book> b = this.bookRepository.findById(query.getId());

        BookModel bm = new BookModel();
        b.ifPresent(x -> {
            bm.setId(x.getId());
            bm.setName(x.getName());
            bm.setAuthor(x.getAuthor());
            bm.setReady(x.isReady());
        });

        return bm;
    }
}
