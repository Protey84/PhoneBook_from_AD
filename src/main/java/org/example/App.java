package org.example;

import org.example.dao.PersonDAO;
import org.example.domain.Person;
import org.example.gui.PassWordDialog;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        ApplicationContext ctx=new AnnotationConfigApplicationContext(AppConfig.class);
        PersonDAO personDAO = (PersonDAO) ctx.getBean("personDAO");
        List<Person> getAllPersons = personDAO.getAllPersons();
        List findUserByCommonName = personDAO.findUserByDepartment("Вологдастат");
        String userName=(String) ctx.getBean("userDn");
        //PassWordDialog passWordDialog=new PassWordDialog(null, true, userName);
        //passWordDialog.setVisible(true);
        //for (Person person:getAllPersons) {
        //    System.out.println(person);
        //}
        new MainWindow(getAllPersons);
        //System.out.println("All user size: " + getAllPersons.size());
        //System.out.println("Found user size: " + findUserByCommonName.size());


    }
}