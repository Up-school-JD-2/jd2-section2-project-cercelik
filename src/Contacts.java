import java.util.Objects;

public class Contacts {

    private final int id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private static int count = 1;

    public Contacts(String name, String surname, String phone, String email) {
        this.id = count++;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
    }
    public int getId() {
        return id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Override
    public String toString() {
        return "Contacts{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contacts contacts)) return false;
        return id == contacts.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
