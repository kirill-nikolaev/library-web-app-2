package ru.library.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.library.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class PersonRowMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();

        person.setId(rs.getInt("person_id"));
        person.setFullName(rs.getString("name"));
        person.setBirthYear(rs.getInt("year"));

        return person;
    }
}
