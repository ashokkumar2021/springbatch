package com.abclimited.springbatch.entity;


import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name= "books_details")
@Data
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;

    private Integer yearOfPublish;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setYearOfPublish(Integer yearOfPublish) {
        this.yearOfPublish = yearOfPublish;
    }

    public Integer getYearOfPublish() {
        return yearOfPublish;
    }
}
