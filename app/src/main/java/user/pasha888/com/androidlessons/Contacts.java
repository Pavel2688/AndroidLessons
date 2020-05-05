package user.pasha888.com.androidlessons;

public class Contacts {
    private final String name;
    private final String phoneNumber;
    private final int image;

    public Contacts(String name, String phoneNumber, int image) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.image = image;
    }



    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getImage() {
        return image;
    }

    @Override
    public String toString() {
        return name;
    }
}
