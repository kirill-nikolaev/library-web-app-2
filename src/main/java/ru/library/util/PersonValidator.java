package ru.library.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.library.dao.PersonDao;
import ru.library.models.Person;

@Component
public class PersonValidator implements Validator {

    PersonDao personDao;

    @Autowired
    public PersonValidator(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        Person personFromDb = personDao.findByName(person.getName());

        if (personFromDb != null && person.getId() != personFromDb.getId()) {
            errors.rejectValue("name", "", "ФИО должно быть уникальным");
        }
    }
}
