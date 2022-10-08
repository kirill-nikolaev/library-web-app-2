package ru.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.library.dao.BookDao;
import ru.library.dao.PersonDao;
import ru.library.models.Book;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleControllers {

    private final PersonDao personDao;
    private final BookDao bookDao;

    @Autowired
    public PeopleControllers(PersonDao personDao, BookDao bookDao) {
        this.personDao = personDao;
        this.bookDao = bookDao;
    }

    @GetMapping("")
    public String showPeople(Model model) {
        model.addAttribute("people", personDao.findAll());
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
    public String showBook(@PathVariable("id") int id,
                           Model model) {
        model.addAttribute("person", personDao.findById(id));
        model.addAttribute("books", bookDao.findByPersonId(id));
        return "people/showPerson";
    }
}
