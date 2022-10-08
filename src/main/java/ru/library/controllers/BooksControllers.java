package ru.library.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("books")
public class BooksControllers {

    @GetMapping("/")
    public String showBooks() {
        return "books/showBooks";
    }

    @GetMapping("/add")
    public String addBook() {
        return "books/add";
    }

    @GetMapping("/{id}/edit")
    public String addBook(@PathVariable("id") int id) {

        return "books/edit";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id) {
        return "people/showPerson";
    }
}
