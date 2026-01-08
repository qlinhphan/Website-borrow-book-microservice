package com.example.bookservices.command.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventUpdateBook {
    private String id;
    private String name;
    private String author;
    private boolean ready;
}
