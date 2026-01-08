package com.example.userservices.query.projection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.userservices.command.data.User;
import com.example.userservices.command.data.UserRepository;
import com.example.userservices.query.model.UserModel;
import com.example.userservices.query.queries.Queries;
import com.example.userservices.query.queries.QueryById;

@Component
public class QueryProjection {

    @Autowired
    private UserRepository userRepository;

    @QueryHandler
    public List<UserModel> handle(Queries queries) {
        List<UserModel> exports = new ArrayList<>();
        List<User> users = this.userRepository.findAll();

        users.forEach(x -> {
            UserModel toAdd = new UserModel();
            toAdd.setId(x.getId());
            toAdd.setFName(x.getFName());
            toAdd.setLName(x.getLName());
            toAdd.setKin(x.getKin());
            toAdd.setDiscipline(x.isDiscipline());
            exports.add(toAdd);
        });

        return exports;
    }

    @QueryHandler
    public UserModel findOne(QueryById queryById) {
        Optional<User> user = this.userRepository.findById(queryById.getId());
        UserModel exp = new UserModel();
        user.ifPresent(x -> {
            exp.setId(x.getId());
            exp.setFName(x.getFName());
            exp.setLName(x.getLName());
            exp.setKin(x.getKin());
            exp.setDiscipline(x.isDiscipline());
        });

        return exp;

    }
}
