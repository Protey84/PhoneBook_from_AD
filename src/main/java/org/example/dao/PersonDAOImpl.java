package org.example.dao;

import org.example.domain.Person;
import org.example.utils.PersonAttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class PersonDAOImpl implements PersonDAO{
    private LdapTemplate ldapTemplate;


    public void setLdapTemplate(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }


    @Override
    public List<Person> getAllPersons(List<Person> search) {
        List<Person> persons = new ArrayList<>();
        try {
            persons.addAll(search);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Comparator<Person> comparator=Comparator.comparing(Person::getDepartment).thenComparing(Person::getName);
        return persons.stream().filter(person -> (person.getName().length()-person.getName().replace(" ", "").length() ==2)).sorted(comparator).collect(Collectors.toList());
    }

    @Override
    public List<Person> findUserByDepartment(String department) {
        AndFilter andFilter = new AndFilter();
        andFilter.and(new EqualsFilter("company", department));
        andFilter.and(new EqualsFilter("objectCategory", "Person"));
        andFilter.and(new EqualsFilter("UserAccountControl:1.2.840.113556.1.4.803:", 512));
        return searchPersonByFilter(andFilter);
    }

    private List<Person> searchPersonByFilter(AndFilter filter){
        List<Person> personList=new ArrayList<>();
        try {
            personList=ldapTemplate.search("", filter.encode(), new PersonAttributesMapper());
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Ошибка авторизации. Проверьте пароль!");
            System.exit(0);
        }
        return personList;
    }
}