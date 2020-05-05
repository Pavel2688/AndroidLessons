package user.pasha888.com.androidlessons;

public class Kontakt {
    private final String name;
    private final String phoneNumber;
    private final int image;

    public Kontakt(String name, String phoneNumber, int image) {
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
