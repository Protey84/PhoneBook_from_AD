package org.example;

import org.example.dao.PersonDAOImpl;
import org.example.gui.PassWordDialog;
import org.example.utils.AppPreferences;
import org.example.utils.PropertyReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

import java.io.FileReader;
import java.io.IOException;
import java.security.Principal;
import java.util.Properties;

@Configuration
public class AppConfig {
    @Bean
        public LdapContextSource contextSource() {
            LdapContextSource contextSource = new LdapContextSource();
            PropertyReader propertyReader=propertyReader();
            contextSource.setUrl(propertyReader.getUrl());
            contextSource.setBase(propertyReader.getBase());
            PassWordDialog passWordDialog=passWordDialog();
            String login= String.format("%s\\%s", propertyReader.getDomain(), passWordDialog.getLogin());
            contextSource.setUserDn(login);
            contextSource.setPassword(new String(passWordDialog.getPassword()));
            return contextSource;
        }
        @Bean
        public LdapTemplate ldapTemplate() {
            LdapTemplate ldapTemplate = new LdapTemplate(contextSource());
            ldapTemplate.setIgnorePartialResultException(true);
            return ldapTemplate;
        }

        @Bean
    public PersonDAOImpl personDAO(){
        PersonDAOImpl personDAO=new PersonDAOImpl();
        personDAO.setLdapTemplate(ldapTemplate());
        return personDAO;
        }

        @Bean
    public PropertyReader propertyReader(){
        PropertyReader propertyReader=new PropertyReader();
            Properties properties = new Properties();
            try(FileReader fileReader = new FileReader("src/main/resources/application.properties")){
                properties.load(fileReader);
            } catch (IOException e) {
                e.printStackTrace();
            }

//получаем значения свойств из объекта Properties
            propertyReader.setUrl(properties.getProperty("url"));
            propertyReader.setBase(properties.getProperty("base"));
            propertyReader.setDomain(properties.getProperty("domain"));
            return propertyReader;
        }

        @Bean
    public String userDn(){
            Principal principal = () -> System.getProperty("user.name");
            return principal.getName();
        }

    private PassWordDialog passWordDialog() {
        AppPreferences appPreferences=new AppPreferences();
        PassWordDialog pwdDialog = new PassWordDialog(null, true, userDn(), appPreferences.getWord());
        pwdDialog.setVisible(true);
        return pwdDialog;
    }
}

