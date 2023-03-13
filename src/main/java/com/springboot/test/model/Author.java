package com.springboot.test.model;


import jakarta.persistence.*;

@Entity
@Table(name = "authors")
public class Author {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    //author's  nickname
    private String pseudonym;

    private String password;


    public Author() {
    }

    public Author(Integer id, String pseudonym, String password) {
        this.id = id;
        this.pseudonym = pseudonym;
        this.password = password;
    }

    public Author(String pseudonym, String password) {
        this.pseudonym = pseudonym;
        this.password = password;
    }

    public static Author newOf(String pseudonym,String password) {
        return new Author(pseudonym, password);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPseudonym() {
        return pseudonym;
    }

    public void setPseudonym(String pseudonym) {
        this.pseudonym = pseudonym;
    }


}
