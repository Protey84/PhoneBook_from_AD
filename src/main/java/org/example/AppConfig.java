package org.example;

import org.example.dao.PersonDAOImpl;
import org.example.gui.PassWordDialog;
import org.example.utils.AppPreferences;
import org.example.utils.FileVerifayer;
import org.example.utils.PropertyReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import java.security.Principal;


@Configuration
@ComponentScan("org.example.gui")
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
            return new PropertyReader(new FileVerifayer("src/main/resources", "application.properties").getFile());
        }

        @Bean
    public String userDn(){
            Principal principal = () -> System.getProperty("user.name");
            return principal.getName();
        }

    @Bean
        public PassWordDialog passWordDialog() {
        AppPreferences appPreferences=new AppPreferences();
        PassWordDialog pwdDialog = new PassWordDialog(null, true, userDn(), appPreferences, propertyReader());
        pwdDialog.setVisible(true);
        return pwdDialog;
    }

}

