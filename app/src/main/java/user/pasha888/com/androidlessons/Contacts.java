package user.pasha888.com.androidlessons;

import java.util.GregorianCalendar;

public class Contacts {
    private final String name;
    private final String telephoneNumber;
    private final  String telephoneNumber2;
    private final  String email;
    private final  String email2;
    private final  String description;
    private final GregorianCalendar dateOfBirth;


    public Contacts(String name, String telephoneNumber, String telephoneNumber2, String email, String email2, String description, GregorianCalendar dateOfBirth) {
        this.name = name;
        this.telephoneNumber = telephoneNumber;
        this.telephoneNumber2 = telephoneNumber2;
        this.email = email;
        this.email2 = email2;
        this.description = description;
        this.dateOfBirth = dateOfBirth;
    }

    public GregorianCalendar getDateOfBirth() {
        return dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getTelephoneNumber2() {
        return telephoneNumber2;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name;
    }
}
