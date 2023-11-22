package org.example.dao;

import org.example.domain.Person;

import java.util.List;

public interface PersonDAO {

    List<Person> getAllPersons(List<Person> foundObjects);

    List<Person> findUserByDepartment(String department);
}
