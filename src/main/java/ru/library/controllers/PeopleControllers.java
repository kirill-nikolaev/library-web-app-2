package ru.library.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("people")
public class PeopleControllers {


    @GetMapping("")
    public String showPeople(Model model) {
        return "people/showPeople";
    }

    @GetMapping("/create")
    public String addPerson() {
        return "people/add";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id) {
        return "people/edit";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id) {
        return "books/showBook";
    }
}
