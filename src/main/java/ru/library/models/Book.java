package ru.library.models;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Component
public class Book {
    private int id;
    @Size(min = 2, message = "Название книги слишком короткое")
    @Size(max = 100, message = "Название книги слишком длинное")
    private String title;
    @Size(min = 2, message = "Имя автора слишком короткое")
    @Size(max = 30, message = "Имя автора слишком длинное")
    private String author;
    @Max(value = 2022, message = "Год не может быть больше 2022")
    @Min(value = 0, message = "Год должен быть больше 0" )
    private int year;
    private Integer personId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }
}
