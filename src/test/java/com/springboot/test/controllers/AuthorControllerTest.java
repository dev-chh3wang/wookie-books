package com.springboot.test.controllers;

import com.springboot.test.author.AuthorService;
import com.springboot.test.model.Author;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthorControllerTest {


    @Autowired
    MockMvc mockMvc;

    @InjectMocks
    AuthorController authorController;

    @MockBean
    AuthorService authorService;


    @BeforeEach
    void setUp() {

    }

    @Test
    void shouldSaveAuthor() throws Exception {

        Author author = new Author(1, "Jack", "pass123");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",author.getId());
        jsonObject.put("pseudonym",author.getPseudonym());
        jsonObject.put("password",author.getPassword());


        when(authorService.save(any(Author.class))).thenReturn(author);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/v1/author/")
                .content(jsonObject.toString())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(author.getId()))
                .andExpect(jsonPath("$.pseudonym").value(author.getPseudonym()))
                .andExpect(jsonPath("$.password").value(author.getPassword()));



    }
}