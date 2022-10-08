package ru.library.models;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Component
public class Person {
    private int id;
    @Pattern(regexp = "[А-Я][a-я]+ [А-Я][a-я]+ [А-Я][a-я]+", message = "Введите корректные данные")
    private String fullName;
    @Min(value = 1900, message = "Год рождения должен быть больше 1900")
    @Max(value =  2020, message = "Год рождения должен быть меньше 2020")
    private int birthYear;

    public Person() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
}
