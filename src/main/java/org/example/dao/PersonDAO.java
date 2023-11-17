package org.example.dao;

import org.example.domain.Person;
import org.springframework.stereotype.Component;

import java.util.List;

public interface PersonDAO {

    public List<Person> getAllPersons(List foundObjects);

    public List findUserByCommonName(String commonName);

    public List findUserByDepartment(String department);
}
