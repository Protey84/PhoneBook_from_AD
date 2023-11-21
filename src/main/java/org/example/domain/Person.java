package org.example.domain;

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

