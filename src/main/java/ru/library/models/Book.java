package ru.library.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
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

    @Column(name = "issuing_time")
    private LocalDateTime issuingTime;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @Transient
    private boolean isExpired;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }
}
