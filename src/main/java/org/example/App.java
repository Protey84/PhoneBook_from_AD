package org.example;

import org.example.dao.PersonDAO;
import org.example.domain.Person;
import org.example.utils.PropertyReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        ApplicationContext ctx=new AnnotationConfigApplicationContext(AppConfig.class);
        List<Person> allPersons = getPersons(ctx);
        new MainWindow(allPersons);
    }

    public static List<Person> getPersons(ApplicationContext ctx){
        PersonDAO personDAO = (PersonDAO) ctx.getBean("personDAO");
        PropertyReader propertyReader = (PropertyReader) ctx.getBean("propertyReader");
        return personDAO.getAllPersons(personDAO.findUserByDepartment(propertyReader.getCompany()));
    }

}