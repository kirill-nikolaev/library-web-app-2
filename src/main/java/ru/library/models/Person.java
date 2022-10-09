package ru.library.models;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Component
public class Person {
    private int id;
    @Pattern(regexp = "[А-Я][а-ё]+ [А-Я][а-ё]+ [А-Я][а-ё]+", message = "Введите корректные данные")
    private String name;
    @Min(value = 1900, message = "Год рождения должен быть больше 1900")
    @Max(value =  2020, message = "Год рождения должен быть меньше 2020")
    private int year;

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
