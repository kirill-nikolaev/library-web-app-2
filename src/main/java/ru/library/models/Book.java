package ru.library.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    @Size(min = 2, message = "Название книги слишком короткое")
    @Size(max = 100, message = "Название книги слишком длинное")
    private String title;

    @Column(name = "author")
    @Size(min = 2, message = "Имя автора слишком короткое")
    @Size(max = 30, message = "Имя автора слишком длинное")
    private String author;

    @Column(name = "year")
    @Max(value = 2022, message = "Год не может быть больше 2022")
    @Min(value = 0, message = "Год должен быть больше 0" )
    private int year;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    public Book() {
    }

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
