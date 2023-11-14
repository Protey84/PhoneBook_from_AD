package org.example;

import org.example.dao.PersonDAO;
import org.example.dao.PersonDAOImpl;
import org.example.utils.AppPreferences;
import org.example.utils.PropertyReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
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
            contextSource.setUserDn(propertyReader.getUserdN());
            contextSource.setPassword(new String(propertyReader.getPassword()));
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
            propertyReader.setUserdN(properties.getProperty("domain")+"\\"+userDn());
            propertyReader.setPassword(properties.getProperty("password").toCharArray());
            return propertyReader;
        }

        @Bean
    public String userDn(){
            Principal principal = new Principal() {
                @Override
                public String getName() {
                    return System.getProperty("user.name");
                }
            };
            return principal.getName();
        }

        @Bean
    public AppPreferences appPreferences(){
        return new AppPreferences();
        }

        private char[] password(){
        AppPreferences appPreferences = new AppPreferences();
        if ("".equals(appPreferences.getWord())){

        }
        }
}

