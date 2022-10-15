package ru.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.library.models.Person;
import ru.library.services.PeopleService;
import ru.library.util.PersonValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleControllers {
    private final PeopleService peopleService;
    private final PersonValidator personValidator;

    @Autowired
    public PeopleControllers(PeopleService peopleService, PersonValidator personValidator) {
        this.peopleService = peopleService;
        this.personValidator = personValidator;
    }


    @GetMapping("")
    public String showPeople(Model model) {
        model.addAttribute("people", peopleService.findAll());
        return "people/showPeople";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id,
                             Model model) {
        model.addAttribute("person", peopleService.findById(id));
        return "people/edit";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id,
                           Model model) {
        Person person = peopleService.findByIdLoadBooks(id);
        model.addAttribute("person", person);
        model.addAttribute("books", person.getBooks());
        return "people/showPerson";
    }

    @PostMapping("/new")
    public String createPerson(@ModelAttribute("person")@Valid Person person,
                            BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/new";
        }
        peopleService.addNew(person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        peopleService.deleteById(id);
        return "redirect:/people";
    }

    @PutMapping("/{id}")
    public String putPerson(@PathVariable("id") int id,
                            @ModelAttribute("person") @Valid Person person,
                            BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/edit";
        }
        peopleService.updateById(id, person);
        return "redirect:/people";
    }
}
