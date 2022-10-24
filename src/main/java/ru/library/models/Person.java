package ru.library.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person")
@Getter
@Setter
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    @Pattern(regexp = "[А-Я][а-ё]+ [А-Я][а-ё]+ [А-Я][а-ё]+", message = "Введите корректные данные")
    private String name;
    @Column(name = "year")
    @Min(value = 1900, message = "Год рождения должен быть больше 1900")
    @Max(value =  2020, message = "Год рождения должен быть меньше 2020")
    private int year;

    @OneToMany(mappedBy = "person")
    private List<Book> books;

    public Person(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public void addBook(Book book) {
        if (this.books == null)
            books = new ArrayList<>();
        books.add(book);
    }
}
