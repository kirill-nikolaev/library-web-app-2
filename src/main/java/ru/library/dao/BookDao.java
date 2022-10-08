package ru.library.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.library.models.Book;

import java.util.List;

@Component
public class BookDao {
    private final JdbcTemplate jdbcTemplate;
    private final BookRowMapper bookRowMapper;
    @Autowired
    public BookDao(JdbcTemplate jdbcTemplate, BookRowMapper bookRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.bookRowMapper = bookRowMapper;
    }

    public List<Book> findAll() {
        return jdbcTemplate.query("SELECT * FROM book", bookRowMapper);
    }
}
