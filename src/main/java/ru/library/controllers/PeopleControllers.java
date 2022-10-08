package ru.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.library.dao.BookDao;
import ru.library.dao.PersonDao;
import ru.library.models.Person;

import javax.validation.Valid;

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

    @GetMapping("/new")
    public String addPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id,
                             @ModelAttribute("person") Person person) {
        return "people/edit";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id,
                           Model model) {
        model.addAttribute("person", personDao.findById(id));
        model.addAttribute("books", bookDao.findByPersonId(id));
        return "people/showPerson";
    }

    @PostMapping("/new")
    public String newPerson(@ModelAttribute("person")@Valid Person person,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "people/new";
        }
        personDao.add(person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        personDao.deleteById(id);
        return "redirect:/people";
    }
}
