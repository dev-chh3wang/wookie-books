package com.springboot.test.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;

import java.math.BigDecimal;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;
    private String description;

    @ManyToOne
    private Author author;
    private String coverImage;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=6, fraction=2)
    private BigDecimal price;

    public Book() {
    }

    public Book(Integer id, String title, String description, Author author, String coverImage, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
        this.coverImage = coverImage;
        this.price = price;
    }

     Book(String title, String description, Author author, String coverImage, BigDecimal price) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.coverImage = coverImage;
        this.price = price;
    }

    public static Book ofNew(String title,String description, Author author,String coverImage, BigDecimal price) {
        return new Book(title,description,author,coverImage,price);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
