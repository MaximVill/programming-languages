package model;

public class Visitor {
    private int id;
    private String name, surname, phone;
    private boolean subscribed;

    public Visitor(int id, String name, String surname, String phone, boolean subscribed) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.subscribed = subscribed;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public String getPhone() { return phone; }
    public boolean isSubscribed() { return subscribed; }
}