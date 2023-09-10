package j2ee.lab04;

public class Address {
    private int id;

    private String address;
    private String city;
    private String pin;

    public Address() {
        // Default constructor
    }

    public Address(String address, String city, String pin) {
        this.address = address;
        this.city = city;
        this.pin = pin;
    }

    public String getAddress() {
        return address;
    }
    public int getId() {
        return id;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Address{" +
                "address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", pin='" + pin + '\'' +
                '}';
    }
}
