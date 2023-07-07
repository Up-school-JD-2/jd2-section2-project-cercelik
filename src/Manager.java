import java.util.*;
import java.util.stream.Collectors;

public class Manager {

    private Map<String, String[]> brands;
    private int[] storage;
    private List<App> apps;
    private Map<Integer, Phone> phones;

    private List<Contacts> contacts;

    public Manager() {
        brands = new HashMap<>();

        String[] appleModels = new String[]{"iPhone 14 Pro Max", "iPhone 14 Pro", "iPhone 14", "iPhone 14 Plus",
                "iPhone 13 Pro", "iPhone 13", "iPhone 13 mini"};

        brands.put(PhoneBrands.APPLE.name(), appleModels);

        String[] samsungModels = new String[]{"Samsung Galaxy S23 Ultra ", "Samsung Galaxy Z Flip 4", "Samsung Galaxy S23+"};

        brands.put(PhoneBrands.SAMSUNG.name(), samsungModels);

        String[] huaweiModels = new String[]{"Huawei P30 Pro", "Huawei Mate 20 Pro", "Huawei Mate 30 Pro"};

        brands.put(PhoneBrands.HUAWEI.name(), huaweiModels);

        storage = new int[]{32, 64, 128, 256, 512, 1024};


        apps = new ArrayList<>();

        apps.add(new App(1, "Google Maps", "1", 10));
        apps.add(new App(2, "Gmail", "2.14", 30));
        apps.add(new App(3, "Twitter", "3.0", 40));
        apps.add(new App(4, "Zoom", "4.01", 50));
        apps.add(new App(5, "Discord", "5.4", 70));
        apps.add(new App(6, "Linkedin", "6.0", 80));
        apps.add(new App(7, "Tabata", "7.1", 90));

        phones = new HashMap<>();

        contacts = new ArrayList<>();


    }

    public List<App> getApps() {
        return apps;
    }

    public String[] getModels(String brand) {
        String[] models = null;
        if (brands.containsKey(brand))
            return brands.get(brand);

        return null;
    }

    public int[] getStorage() {
        return storage;
    }

    public void addPhone(Phone phone) {
        phones.put(phone.getId(), phone);
    }

    public void getPhoneDetails() {
        phones.forEach((key, value) -> System.out.println(key + " -> " + value));
    }

    public Phone getPhone(){
        return getPhone();
    }
    public Phone selectAPhone(int selectedPhoneID) throws NoPhoneFound{

        isPhoneExists();

        Phone selectedPhone = null;
        if (phones.containsKey(selectedPhoneID)) {
            selectedPhone = phones.get(selectedPhoneID);
            System.out.println("Phone selected.");
        } else {
            System.out.println("Wrong id");
        }
        return selectedPhone;
    }

    public void installApps(Phone phone, int id) {

        Optional<App> selectedApp = getApps().stream().filter(x -> x.getId() == id).findFirst();

        if (selectedApp.isEmpty()) {
            System.out.println("There is no application found in the app store. ");
            return;
        }

        if (phone.getAppList().contains(selectedApp.get())) {
            System.out.println("Selected app already installed. ");
            return;
        }

        if (getPhoneRemainedStorage(phone) < selectedApp.get().getSize()) {
            System.out.println("Not enough space to install selected app. ");
        } else {
            phone.getAppList().add(selectedApp.get());
            System.out.println("App installed successfully. ");
        }
    }

    public void removeInstalledApp(Phone phone, int id) {

        Optional<App> tobeRemovedApp = getApps().stream().filter(x -> x.getId() == id).findFirst();
        System.out.println();

        if (tobeRemovedApp.isEmpty()) {
            System.out.println("id not found");
        } else if (!(phone.getAppList().contains(tobeRemovedApp.get()))) {
            System.out.println(tobeRemovedApp.get().getName() + " is not installed");
        } else {
            phone.getAppList().removeIf(x -> x.getId() == id);
            System.out.println("Selected app has been uninstalled. ");
        }

    }

    public boolean isAppExists(int id) {
        return getApps().stream().anyMatch(x -> x.getId() == id);

    }

    public void addContact(Phone phone, Contacts contact) {
        if (!phone.getContactList().contains(contact)) {
            phone.getContactList().add(contact);
        } else {
            System.out.println("Contact exists.");
        }
    }

    public void updateContactNumber(Phone phone, int id, String phoneNumber) {

        Optional<Contacts> selectedContact = phone.getContactList().stream().filter(x -> x.getId() == id).findFirst();

        if (selectedContact.isEmpty()) {
            System.out.println("id not found");

        } else {
            selectedContact.get().setPhone(phoneNumber);
            System.out.println("Phone number updated successfully. ");
        }


    }

    public void removeContact(Phone phone, int id) {
        Optional<Contacts> tobeRemovedContact = phone.getContactList().stream().filter(x -> x.getId() == id).findFirst();

        if (tobeRemovedContact.isEmpty()) {
            System.out.println("id not found");
        } else {
            phone.getContactList().removeIf(x -> x.getId() == id);
            System.out.println("Selected contact has been deleted. ");
        }
    }

    public boolean isContactExists(Phone phone, int id) {
        return phone.getContactList().stream().anyMatch(x -> x.getId() == id);
    }

    public boolean isPhoneExists() throws NoPhoneFound{
        if(phones.isEmpty()) {
         throw new NoPhoneFound();
        }
        return true;
    }

    public String generateSerialNumber() {
        String serialNumber = "S/N" + UUID.randomUUID().toString();
        return serialNumber;
    }

    public double getPhoneUsedStorage(Phone phone) {
        return phone.getAppList().stream().
                mapToDouble(x -> x.getSize()).sum();
    }

    public double getPhoneRemainedStorage(Phone phone) {
        return phone.getStorage() - getPhoneUsedStorage(phone);
    }

    public List<App> getInstalledAppsBySize(Phone phone) {
        List<App> sortedList = phone.getAppList().stream()
                .sorted(Comparator.comparingInt(App::getSize)).collect(Collectors.toList());
        return sortedList;
    }


}
