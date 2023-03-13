package com.springboot.test.author;

import com.springboot.test.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService  {


    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    public Optional<Author> findById(Integer id) {
        return authorRepository.findById(id);
    }


    public Author save(Author author) {
        return authorRepository.save(author);
    }
}
