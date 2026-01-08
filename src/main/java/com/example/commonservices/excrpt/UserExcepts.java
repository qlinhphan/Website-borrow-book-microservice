package com.example.commonservices.excrpt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.commonservices.dto.ExpDTO;

@ControllerAdvice
public class UserExcepts {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> handles(MethodArgumentNotValidException ex) {
        ExpDTO dto = new ExpDTO();
        dto.setStt(400);
        dto.setError("cannot add a user");

        BindingResult br = ex.getBindingResult();
        List<FieldError> ers = br.getFieldErrors();
        List<String> toAdd = new ArrayList<>();
        ers.forEach(x -> {
            toAdd.add(x.getDefaultMessage());
        });

        dto.setMessage(toAdd);

        return ResponseEntity.status(400).body(dto);
    }
}
