package ru.library.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.library.dao.BookDao;
import ru.library.dao.PersonDao;
import ru.library.models.Book;
import ru.library.models.Person;


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
        return "books/showBooks";
    }

    @GetMapping("/new")
    public String addBook() {
        //TODO
        return "books/new";
    }

    @GetMapping("/{id}/edit")
    public String addBook(@PathVariable("id") int id) {
        //TODO
        return "books/edit";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model) {
        Book book = bookDao.findById(id);

        model.addAttribute("book", book);
        model.addAttribute("isUsed", book.getPersonId() != null);

        if (book.getPersonId() == null)
            model.addAttribute("people", personDao.findAll());
        else
            model.addAttribute("person", personDao.findById(book.getPersonId()));

        return "books/showBook";
    }

    @PostMapping("/new")
    public String addBook(@ModelAttribute("book")Book book){
        //TODO
        return "redirect:/books";
    }

    @PutMapping("/{id}")
    public String editBook(@PathVariable("id") int id) {
        //TODO
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        //TODO
        return "redirect:/books";
    }

    @PatchMapping("/{id}")
    public String changePersonId(@PathVariable("id") int id,
                                 @RequestParam("personId") int personId) {
        if (personId == - 1)
            bookDao.setPersonId(id, null);
        else
            bookDao.setPersonId(id, personId);
        return "redirect:/books/" + id;
    }
}
