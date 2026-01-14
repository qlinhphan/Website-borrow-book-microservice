package com.example.commonservices.command.book;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventUpdateSttBook {
    private String id;
    private String name;
    private String author;
    private boolean ready;
}
