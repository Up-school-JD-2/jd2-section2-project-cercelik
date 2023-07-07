import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.util.Objects;

public class Phone implements Serializable{
    private  int id;
    private String brand;
    private String model;
    private final String serialNumber;
    private double storage;
    private String operatingSystem;

    private List<App> appList;

    private List<Contacts> contactList;

    private static int count =1;

    public Phone(String brand, String model, String serialNumber, double storage, String operatingSystem) {
        this.id = count++;
        this.brand = brand;
        this.model = model;
        this.serialNumber = serialNumber;
        this.storage = storage;
        this.operatingSystem = operatingSystem;
        appList = new ArrayList<>();
        contactList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public double getStorage() {
        return storage;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }


    public List<App> getAppList() {
        return appList;
    }

    public List<Contacts> getContactList() {
        return contactList;
    }
    
    @Override
    public String toString() {
        return "Phone{" +
                "id='" + id + '\'' +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", storage=" + storage +
                ", operatingSystem='" + operatingSystem + '\'' +
                ", appList=" + appList + '\'' +
                ", contactList=" + contactList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Phone phone)) return false;
        return id == phone.id && serialNumber.equals(phone.serialNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, serialNumber);
    }

}
