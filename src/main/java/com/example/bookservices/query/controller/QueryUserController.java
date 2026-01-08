package com.example.bookservices.query.controller;

import java.util.List;

import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.bookservices.query.model.BookModel;
import com.example.bookservices.query.queries.ById;
import com.example.bookservices.query.queries.GetAll;

@RestController
@RequestMapping("/v1/books")
public class QueryUserController {

    @Autowired
    private QueryGateway queryGateway;

    @GetMapping
    public List<BookModel> findAlls() {
        GetAll queries = new GetAll();
        return this.queryGateway.query(queries, ResponseTypes.multipleInstancesOf(BookModel.class)).join();
    }

    @GetMapping("/{id}")
    public BookModel findId(@PathVariable("id") String id) {
        ById query = new ById();
        query.setId(id);

        return this.queryGateway.query(query, ResponseTypes.instanceOf(BookModel.class)).join();
    }
}
