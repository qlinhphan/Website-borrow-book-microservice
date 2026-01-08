package com.example.bookservices.query.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookModel {
    private String id;
    private String name;
    private String author;
    private boolean ready;
}
