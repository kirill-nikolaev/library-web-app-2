package ru.library.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.library.models.Book;

import java.util.List;

@Component
public class BookDao {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> findAll() {
        return jdbcTemplate.query("SELECT * FROM book", new BookRowMapper());
    }

    public Book findById(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE book_id = ?",
                new Object[]{id}, new BookRowMapper()).stream().findAny().orElse(null);
    }

    public List<Book> findByPersonId(int personId) {
        return jdbcTemplate.query("SELECT * FROM book WHERE person_id = ?",
                new Object[]{personId}, new BookRowMapper());
    }

    public void setPersonId(int bookId, Integer personId) {
        jdbcTemplate.update("UPDATE book SET person_id = ? WHERE book_id = ?", personId, bookId);
    }

}
