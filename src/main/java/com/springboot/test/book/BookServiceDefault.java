package com.springboot.test.book;

import com.springboot.test.book.BookRepository;
import com.springboot.test.book.BookService;
import com.springboot.test.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceDefault implements BookService {


    private final BookRepository bookRepository;

    @Autowired
    public BookServiceDefault(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public List<Book> findByAuthorId(Integer authorId) {
        return bookRepository.findByAuthorId(authorId);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> getByid(Integer id) {
        return bookRepository.findById(id);
    }
}
