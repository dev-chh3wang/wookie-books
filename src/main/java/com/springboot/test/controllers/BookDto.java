package com.springboot.test.controllers;

import com.springboot.test.model.Book;

public record BookDto(Integer id, String title, String description, String author, Integer authorId, String img, Double price) {
    public static BookDto of(Book bk) {
        return new BookDto(bk.getId(),bk.getTitle(),bk.getDescription(),bk.getAuthor().getPseudonym(),bk.getAuthor().getId(),bk.getCoverImage(),bk.getPrice().doubleValue());
    }
}
