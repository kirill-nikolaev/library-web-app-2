package ru.library.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.library.models.Book;
import ru.library.models.Person;
import ru.library.services.BooksService;
import ru.library.services.PeopleService;


import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/books")
public class BooksControllers {
    private final BooksService booksService;

    private final PeopleService peopleService;

    @Autowired
    public BooksControllers(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }

    @GetMapping("")
    public String showBooks(Model model) {
        model.addAttribute("books", booksService.findAll());
        return "books/showBooks";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id,
                           Model model) {
        model.addAttribute("book", booksService.findById(id));
        return "books/edit";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model) {
        Book book = booksService.findById(id);

        model.addAttribute("book", book);
        model.addAttribute("isUsed", book.getPerson() != null);

        if (book.getPerson() == null)
            model.addAttribute("people", peopleService.findAll());
        else
            model.addAttribute("person", book.getPerson());
        return "books/showBook";
    }

    @PostMapping("/new")
    public String createBook(@ModelAttribute("book")@Valid Book book,
                             BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "/books/new";
        booksService.addNew(book);
        return "redirect:/books";
    }

    @PutMapping("/{id}")
    public String putBook(@PathVariable("id") int id,
                          @ModelAttribute("book")@Valid Book book,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        booksService.updateById(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        booksService.deleteById(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}")
    public String changePersonId(@PathVariable("id") int id,
                                 @RequestParam(value = "personId", required = false) Integer personId) {
        Person person = null;
        if (personId != null)
            person = peopleService.findById(personId);

        booksService.setPerson(id, person);
        return "redirect:/books/" + id;
    }
}
