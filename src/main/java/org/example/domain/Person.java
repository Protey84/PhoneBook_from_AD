package org.example.domain;

import java.util.Arrays;
import java.util.HashSet;

public class Person {
    private String name;
    private String telephoneNumber;
    private String ipPhone;
    private String streetAddress;
    private String mail;
    private String position;
    private String office;
    private String description;
    private String department;

    public String getDescription() {
        return description;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getIpPhone() {
        return ipPhone;
    }

    public void setIpPhone(String ipPhone) {
        this.ipPhone = ipPhone;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public boolean contains(String search){
        String summ=String.join(" ", this.name, this.mail, this.department, this.telephoneNumber, this.streetAddress, this.description).toLowerCase();
        return search==null||search.isEmpty()||summ.contains(search.toLowerCase())||summ.contains(convert(search));
    }

    public String convert(String message) {
        boolean result = message.matches(".*\\p{InCyrillic}.*");
        char[] ru = {'й','ц','у','к','е','н','г','ш','щ','з','х','ъ','ф','ы','в','а','п','р','о','л','д','ж','э', 'я','ч', 'с','м','и','т','ь','б', 'ю','.'};
        char[] en = {'q','w','e','r','t','y','u','i','o','p','[',']','a','s','d','f','g','h','j','k','l',';','"','z','x','c','v','b','n','m',',','.','/'};
        StringBuilder builder = new StringBuilder();

        if (result) {
            for (int i = 0; i < message.length(); i++) {
                for (int j = 0; j < ru.length; j++ ) {
                    if (message.charAt(i) == ru[j]) {
                        builder.append(en[j]);
                    }
                }
            }
        } else {
            for (int i = 0; i < message.length(); i++) {
                for (int j = 0; j < en.length; j++ ) {
                    if (message.charAt(i) == en[j]) {
                        builder.append(ru[j]);
                    }
                }
            }
        }

        return builder.toString();
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", ipPhone='" + ipPhone + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                ", mail='" + mail + '\'' +
                ", position='" + position + '\'' +
                ", office='" + office + '\'' +
                ", description='" + description + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}

class Switcher{
    static char[] ru = "ёйцукенгшщзхъфывапролджэячсмитьбю".toCharArray();
    static char[] en = "`qwertyuiop[]asdfghjkl;'zxcvbnm,.".toCharArray();
    static HashSet<Character> ruSet=new HashSet<>();
    static HashSet<Character> enSet=new HashSet<>();
    static {
        for (Character ch:ru) {
            ruSet.add(ch);
        }
        for (Character ch:en) {
            enSet.add(ch);
        }
    }
    static String switchToDifferentLayout(String message) {
        char[] symbolsMessage=message.toLowerCase().toCharArray();
        System.out.println(Arrays.toString(ru));
        System.out.println(Arrays.toString(en));
        System.out.println(Arrays.toString(symbolsMessage));
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            if (ruSet.contains(symbolsMessage[i])){
                builder.append(switchChar(symbolsMessage[i], ru, en));
            } else if (enSet.contains(symbolsMessage[i])) {
                builder.append(switchChar(symbolsMessage[i], en, ru));
            }
            //else
               // builder.append(symbolsMessage[i]);
        }
        return builder.toString();
    }
    static char switchChar(char ch, char[] from, char[] to){
        for (int i = 0; i < from.length; i++) {
            if (ch==from[i]){
                return to[i];
            }
        }
        return ch;
    }
}