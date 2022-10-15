package ru.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.library.models.Book;
import ru.library.models.Person;
import ru.library.repositories.PeopleRepository;

import java.util.List;

@Service
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Transactional(readOnly = true)
    public List<Person> findAll(){
        return peopleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Person findById(int id) {
        return peopleRepository.findById(id).orElse(null);
    }
    @Transactional(readOnly = true)
    public Person findByIdLoadBooks(int id) {
        Person person = peopleRepository.findById(id).orElse(null);
        System.out.println(person.getBooks());
        return person;
    }

    @Transactional(readOnly = true)
    public Person findByName(String name) {
        return peopleRepository.findByName(name).get(0);
    }

    @Transactional
    public void addNew(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void deleteById(int id) {
        peopleRepository.deleteById(id);
    }

    @Transactional
    public void updateById(int id, Person person) {
        person.setId(id);
        peopleRepository.save(person);
    }
}
