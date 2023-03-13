package com.springboot.test.book;


import com.springboot.test.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {


    List<Book> findAll();
    List<Book> findByAuthorId(Integer authorId);

    Book save(Book book);
    Optional<Book> getByid(Integer id);
}
