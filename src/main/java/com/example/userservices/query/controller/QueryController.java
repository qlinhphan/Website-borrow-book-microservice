package com.example.userservices.query.controller;

import java.util.List;

import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.userservices.query.model.UserModel;
import com.example.userservices.query.queries.Queries;
import com.example.userservices.query.queries.QueryById;

import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/v1/users")
public class QueryController {

    @Autowired
    private QueryGateway queryGateway;

    @GetMapping
    public List<UserModel> findAlls() {
        Queries queries = new Queries();
        return this.queryGateway.query(queries, ResponseTypes.multipleInstancesOf(UserModel.class)).join();
    }

    @GetMapping("/{id}")
    public UserModel getMethodName(@PathVariable("id") String id) {
        QueryById queryById = new QueryById();
        queryById.setId(id);

        return this.queryGateway.query(queryById, ResponseTypes.instanceOf(UserModel.class)).join();
    }

}
