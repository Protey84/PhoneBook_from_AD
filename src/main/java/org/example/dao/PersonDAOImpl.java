package org.example.dao;

import org.example.domain.Person;
import org.example.utils.PersonAttributesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tugrul on 11.02.2014.
 */
public class PersonDAOImpl implements PersonDAO{
    @Autowired
    private LdapTemplate ldapTemplate;


    public void setLdapTemplate(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    protected final static String baseDN = "OU=TOGS";

    @Override
    public List<Person> getAllPersons() {
        List<Person> persons = new ArrayList<Person>();
        try {
            List search = ldapTemplate.search("", "(&(sn=*)(objectCategory=Person)((UserAccountControl:1.2.840.113556.1.4.803:=512))(givenName=*)(company=Вологдастат))", new PersonAttributesMapper());
            persons.addAll(search);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return persons;
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
        andFilter.and(new EqualsFilter("UserAccountControl:1.2.840.113556.1.4.803:", 0));
        return ldapTemplate.search("", andFilter.encode(), new PersonAttributesMapper());
    }
}