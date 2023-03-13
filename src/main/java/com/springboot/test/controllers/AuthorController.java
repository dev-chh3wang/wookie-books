package com.springboot.test.controllers;

import com.springboot.test.author.AuthorService;
import com.springboot.test.book.BookService;
import com.springboot.test.model.Author;
import com.springboot.test.model.Book;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/api/v1/author/")
public class AuthorController {


    private final AuthorService authorService;
    private final BookService bookService;





    @Autowired
    public AuthorController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;

    }


    @GetMapping(value = "{id}")
    public ResponseEntity<Author> getById(@PathVariable Integer id){
        return ResponseEntity.of(this.authorService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Author> create(@RequestBody Author author){
        Author save = this.authorService.save(author);
        if(save == null){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }



    @PostConstruct
    public void loadData(){
        Author jack = authorService.save(Author.newOf("Jack", encode("pass123")));
        authorService.save(Author.newOf("jane",encode("pass321")));
        Author tommy = authorService.save(Author.newOf("tommy", encode("pass")));
        authorService.save(Author.newOf("Darth Vader", encode("pass-vader")));


        bookService.save(Book.ofNew("Awesome book 1","my awesome book vol1 ",jack,"awesome.jpg", BigDecimal.valueOf(55.23)));
        bookService.save(Book.ofNew(" book 2","awesome book 2 ",jack,"awesome.jpg", BigDecimal.valueOf(55.23)));
        bookService.save(Book.ofNew(" book 3","my awesome book 3",tommy,"awesome.jpg", BigDecimal.valueOf(55.23)));
        bookService.save(Book.ofNew(" book 4","my awesome book 3 ",tommy,"awesome.jpg", BigDecimal.valueOf(55.23)));
    }

    private String encode(String pass) {
        return pass;
    }
}
