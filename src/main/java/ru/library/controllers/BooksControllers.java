package ru.library.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.library.dao.BookDao;
import ru.library.dao.PersonDao;



@Controller
@RequestMapping("/books")
public class BooksControllers {
    private final BookDao bookDao;
    private final PersonDao personDao;

    @Autowired
    public BooksControllers(BookDao bookDao, PersonDao personDao) {
        this.bookDao = bookDao;
        this.personDao = personDao;
    }

    @GetMapping("")
    public String showBooks(Model model) {
        model.addAttribute("books", bookDao.findAll());
        System.out.println(bookDao.findAll().get(0).getPersonId());
        return "books/showBooks";
    }

    @GetMapping("/new")
    public String addBook() {
        return "books/new";
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
