package org.example.dao;

import org.example.domain.Person;
import org.example.utils.PersonAttributesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class PersonDAOImpl implements PersonDAO{
    @Autowired
    private LdapTemplate ldapTemplate;


    public void setLdapTemplate(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }


    @Override
    public List<Person> getAllPersons(List search) {
        List<Person> persons = new ArrayList<Person>();
        try {
            //List search = ldapTemplate.search("", "(&(sn=*)(objectCategory=Person)((UserAccountControl:1.2.840.113556.1.4.803:=512))(givenName=*)(company=Вологдастат))", new PersonAttributesMapper());
            persons.addAll(search);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        Comparator<Person> comparator=Comparator.comparing(Person::getDepartment).thenComparing(Person::getName);
        return persons.stream().filter(person -> (person.getName().length()-person.getName().replace(" ", "").length() ==2)).sorted(comparator).collect(Collectors.toList());
    }

    @Override
    public List findUserByCommonName(String commonName) {
        AndFilter andFilter = new AndFilter();
        andFilter.and(new EqualsFilter("objectclass","person"));
        andFilter.and(new EqualsFilter("cn", commonName));
        return ldapTemplate.search("", andFilter.encode(), new PersonAttributesMapper());
    }

    @Override
    public List findUserByDepartment(String department) {
        AndFilter andFilter = new AndFilter();
        andFilter.and(new EqualsFilter("company", department));
        andFilter.and(new EqualsFilter("objectCategory", "Person"));
        andFilter.and(new EqualsFilter("UserAccountControl:1.2.840.113556.1.4.803:", 512));
        return ldapTemplate.search("", andFilter.encode(), new PersonAttributesMapper());
    }
}