package com.example.example.okta;

import com.example.example.okta.model.Event;
import com.example.example.okta.model.Group;
import com.example.example.okta.model.GroupRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

@Component
class Initializer
        implements CommandLineRunner {

    private final GroupRepository repository;

    public Initializer(GroupRepository repository){
        this.repository = repository;
    }
    @Override
    public void run(String... strings){
        Stream.of("Denver",
                "Utah",
                "Seattle",
                "etc",
                "beirute"
        ).forEach(name ->
                repository.save(new Group(name)));

        Group djug = repository.findByName("etc");
        Event e = Event.builder()
                .title("Test")
                .description("Test desc")
                .date(Instant.parse("2018-12-12T18:00:00.000Z"))
                .build();
        djug.setEvents(Collections.singleton(e));
        repository.save(djug);
        repository.findAll()
                .forEach(System.out::println);
    }
}
