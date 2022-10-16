package ru.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.library.models.Book;
import ru.library.models.Person;
import ru.library.repositories.BooksRepository;

import java.util.List;

@Service
public class BooksService {
    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }


    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Book> findAll(boolean isSorted) {
        return booksRepository.findAll(Sort.by("year"));
    }

    @Transactional(readOnly = true)
    public List<Book> findAll(int page, int booksPerPage, boolean isSorted) {
        Page<Book> bookPage;
        if (isSorted)
             return booksRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("year")))
                     .getContent();
        else
            return booksRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
    }

    @Transactional(readOnly = true)
    public Book findById(int id) {
        return booksRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Book> findByTitleStartingWith(String startWith) {
        return booksRepository.findByTitleStartingWith(startWith);
    }

    @Transactional
    public void addNew(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void updateById(int id, Book book) {
        book.setId(id);
        booksRepository.save(book);
    }

    @Transactional
    public void deleteById(int id) {
        booksRepository.deleteById(id);
    }

    @Transactional
    public void setPerson(int id, Person person) {
        Book book = booksRepository.findById(id).orElse(null);
        if (book != null)
            book.setPerson(person);
    }

}
