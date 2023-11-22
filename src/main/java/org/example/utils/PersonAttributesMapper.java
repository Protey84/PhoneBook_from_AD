package org.example.utils;

import org.example.domain.Person;
import org.springframework.ldap.core.AttributesMapper;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

public class PersonAttributesMapper implements AttributesMapper<Person>{

    @Override
    public Person mapFromAttributes(Attributes attributes) throws NamingException {
        Person person = new Person();

        Attribute name = attributes.get("name");
        if (name != null){
            person.setName((String) name.get());
        }

        Attribute telephoneNumber = attributes.get("telephoneNumber");
        if (telephoneNumber != null){
            person.setTelephoneNumber((String) telephoneNumber.get());
        }

        Attribute ipPhone = attributes.get("ipPhone");
        if (ipPhone != null){
            person.setIpPhone((String) ipPhone.get());
        }

        Attribute streetAddress = attributes.get("streetAddress");
        if (streetAddress != null){
            person.setStreetAddress((String) streetAddress.get());
        }

        Attribute mail = attributes.get("mail");
        if (mail != null){
            person.setMail((String) mail.get());
        }

        Attribute title = attributes.get("title");
        if (title != null){
            person.setPosition(((String) title.get()));
        }

        Attribute physicalDeliveryOfficeName = attributes.get("physicalDeliveryOfficeName");
        if (physicalDeliveryOfficeName != null){
            person.setOffice((String) physicalDeliveryOfficeName.get());
        }

        Attribute department = attributes.get("department");
        if (department != null){
            person.setDepartment(((String) department.get()));
        }

        Attribute description = attributes.get("description");
        if (description != null){
            person.setDescription(((String) description.get()));
        }

        return person;
    }
}