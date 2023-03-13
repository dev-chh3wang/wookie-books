package com.springboot.test.book;

import com.springboot.test.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer> {

    List<Book> findByAuthorId(Integer authorId);
}
