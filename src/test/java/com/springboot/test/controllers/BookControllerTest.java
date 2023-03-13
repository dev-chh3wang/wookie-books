package com.springboot.test.controllers;

import com.springboot.test.book.BookService;
import com.springboot.test.model.Author;
import com.springboot.test.model.Book;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {



    @Autowired
    MockMvc mockMvc;

    @InjectMocks
    BookController bookController;

    @MockBean
    BookService bookService;


    @Test
    void shouldGetAllBooks() throws Exception {


        Author author = new Author(1,"jack","pass");
        Book b1 = new Book(1, "book1", "des1", author, "img1.jpg", new BigDecimal(20.21));
        Book b2 = new Book(2, "book2", "des3", author, "img2.jpg", new BigDecimal(21.21));
        List<Book> books = Arrays.asList(b1,b2);
        when(bookService.findAll()).thenReturn(books);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/v1/books/get-all");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(b1.getId()))
                .andExpect(jsonPath("$[0].title").value(b1.getTitle()))
                .andExpect(jsonPath("$[0].description").value(b1.getDescription()))
                .andExpect(jsonPath("$[0].authorId").value(b1.getAuthor().getId()))
                .andExpect(jsonPath("$[0].author").value(b1.getAuthor().getPseudonym()))
                .andExpect(jsonPath("$[0].img").value(b1.getCoverImage()))
                .andExpect(jsonPath("$[0].price").value(b1.getPrice().doubleValue()));



    }

    @Test
    void shouldGetBooksByAuthor() throws Exception {
        Author author = new Author(1,"jack","pass");
        Book b1 = new Book(1, "book1", "des1", author, "img1.jpg", new BigDecimal(20.21));
        Book b2 = new Book(2, "book2", "des3", author, "img2.jpg", new BigDecimal(21.21));
        List<Book> books = Arrays.asList(b1,b2);

        when(bookService.findByAuthorId(author.getId())).thenReturn(books);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/v1/books/get-by-author/"+author.getId());

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(b1.getId()))
                .andExpect(jsonPath("$[0].title").value(b1.getTitle()))
                .andExpect(jsonPath("$[0].description").value(b1.getDescription()))
                .andExpect(jsonPath("$[0].authorId").value(b1.getAuthor().getId()))
                .andExpect(jsonPath("$[0].author").value(b1.getAuthor().getPseudonym()))
                .andExpect(jsonPath("$[0].img").value(b1.getCoverImage()))
                .andExpect(jsonPath("$[0].price").value(b1.getPrice().doubleValue()));
    }

    @Test
    void shouldGetBooksId() throws Exception {
        Author author = new Author(1,"jack","pass");
        Book b1 = new Book(1, "book1", "des1", author, "img1.jpg", new BigDecimal(20.21));


        when(bookService.getByid(b1.getId())).thenReturn(Optional.of(b1));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/v1/books/get-by/"+b1.getId());

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(b1.getId()))
                .andExpect(jsonPath("$.title").value(b1.getTitle()))
                .andExpect(jsonPath("$.description").value(b1.getDescription()))
                .andExpect(jsonPath("$.authorId").value(b1.getAuthor().getId()))
                .andExpect(jsonPath("$.author").value(b1.getAuthor().getPseudonym()))
                .andExpect(jsonPath("$.img").value(b1.getCoverImage()))
                .andExpect(jsonPath("$.price").value(b1.getPrice().doubleValue()));
    }
}