package com.springboot.test.controllers;

import com.springboot.test.book.BookService;
import com.springboot.test.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/books/")
public class BookController {


    private final BookService bookService;

    Logger logger = LoggerFactory.getLogger(BookController.class);
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }



    @RequestMapping(value = "get-all", method = RequestMethod.GET)
    public ResponseEntity<List<BookDto>> findAll(){
        List<Book> all = this.bookService.findAll();
        List<BookDto> collect = all.stream().map(mapToDto()).collect(Collectors.toList());
        return collect.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(collect, HttpStatus.OK);
    }

    @RequestMapping(value = "get-by-author/{id}",method = RequestMethod.GET)
    public ResponseEntity<List<BookDto>> findBookByAuthor(@PathVariable Integer id){
        logger.debug("getting list by author id {}",id);
        List<Book> byAuthor = this.bookService.findByAuthorId(id);
        List<BookDto> collect = byAuthor.stream().map(mapToDto()).collect(Collectors.toList());
        return collect.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(collect, HttpStatus.OK);
    }


    @RequestMapping(value = "get-by/{id}",method = RequestMethod.GET)
    public ResponseEntity<BookDto> findBook(@PathVariable Integer id){
        Optional<Book> byid = this.bookService.getByid(id);
        return byid.isPresent() ? new ResponseEntity<>(BookDto.of(byid.get()),HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }

    private static Function<Book, BookDto> mapToDto() {
        return bk -> BookDto.of(bk);
    }

}
