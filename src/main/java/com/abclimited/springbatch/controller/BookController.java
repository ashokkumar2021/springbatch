package com.abclimited.springbatch.controller;


import com.abclimited.springbatch.entity.BookEntity;
import com.abclimited.springbatch.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


//http:localhost:8080/books
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public List<BookEntity> getAll() {
        return bookRepository.findAll();
    }
}
