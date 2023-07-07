import java.util.Scanner;

public class Main {

    private static Manager manager;

    public static void main(String[] args) {

        manager = new Manager();

        System.out.println("Welcome to the phone book application! ");

        printMenu();

        selectOperationFromMenu();

    }

    private static void printMenu() {

        System.out.println();
        System.out.println("*****************************");
        System.out.println("1- Add a mobile phone: ");
        System.out.println("2- Add an application: ");
        System.out.println("3- Remove an application: ");
        System.out.println("4- Print installed applications: ");
        System.out.println("5- Add a contact: ");
        System.out.println("6- Update a contact: ");
        System.out.println("7- Remove a contact: ");
        System.out.println("8- Print saved contacts: ");
        System.out.println("9- Check phone storage: ");
        System.out.println("10- Check remained storage: ");
        System.out.println("11- Get App List installed sorted by size:  ");
        System.out.println("12-  Backup your phone: ");
        System.out.println("13- Restore your phone: ");
        System.out.println("*****************************");
        System.out.println();

    }

    private static void selectOperationFromMenu() {

        do {
            int userChoice = -1;
            System.out.println();
            System.out.println("Please select what to do (1-13)");
            try {
                Scanner s = new Scanner(System.in);
                userChoice = s.nextInt();

            } catch (Exception exception) {
                System.out.println("Please enter a number.");
                continue;
            }

            switch (userChoice) {
                case 1:
                    createPhone();
                    break;
                case 2:
                    installApp();
                    break;
                case 3:
                    removeInstalledApp();
                    break;
                case 4:
                    printInstalledApp();
                    break;
                case 5:
                    addContact();
                    break;
                case 6:
                    updateContactNumber();
                    break;
                case 7:
                    removeContact();
                    break;
                case 8:
                    printAddedContacts();
                    break;
                case 9:
                    getPhoneStorage();
                    break;
                case 10:
                    getPhoneRemainedStorage();
                    break;
                case 11:
                    getInstalledAppsBySize();
                    break;
                case 12:
                    backupPhone();
                    break;
                case 13:
                    writeFile();
                    break;
                default:
                    System.out.println("Wrong input. Please enter a number from 1 to 12. ");
            }
            printMenu();
        }
        while (true);
    }

    private static Phone createPhone() {

        int userBrandSelection;
        String selectedBrand;

        PhoneBrands brands[] = PhoneBrands.values();

        for (int i = 0; i < brands.length; i++) {
            System.out.println((i + 1) + " - " + brands[i]);
        }

        do {
            System.out.println("Please select a phone brand. ");
            Scanner s = new Scanner(System.in);
            userBrandSelection = s.nextInt();

        } while ((userBrandSelection - 1) < 0 || (userBrandSelection - 1) >= brands.length);

        selectedBrand = String.valueOf(brands[userBrandSelection - 1]);

        System.out.println("Selected brand is: " + selectedBrand);
        System.out.println("******");

        //model selection
        int userModelSelection;
        String selectedModel;

        String models[] = manager.getModels(selectedBrand);

        for (int i = 0; i < models.length; i++) {
            System.out.println((i + 1) + " - " + models[i]);
        }

        do {
            System.out.println("Please select " + selectedBrand + " model");
            Scanner s = new Scanner(System.in);
            userModelSelection = s.nextInt();

        } while ((userModelSelection - 1) < 0 || (userModelSelection - 1) >= models.length);

        selectedModel = models[userModelSelection - 1];

        System.out.println("Selected model is: " + selectedModel);
        System.out.println("******");

        int userStorageSelection;
        int selectedStorage;

        int[] storage = manager.getStorage();

        do {
            System.out.println("Please select a storage size. ");

            for (int i = 0; i < storage.length; i++) {
                System.out.println((i + 1) + " - " + storage[i] + " GB");
            }
            Scanner s = new Scanner(System.in);
            userStorageSelection = s.nextInt();
        } while ((userStorageSelection - 1) < 0 || (userStorageSelection - 1) >= storage.length);

        selectedStorage = storage[userStorageSelection - 1];
        System.out.println("Selected size is: " + selectedStorage);
        System.out.println("******");

        System.out.println("Please type an OS ");
        Scanner s = new Scanner(System.in);
        String selectedOperatingSystem = s.nextLine();

        String serialNumber = manager.generateSerialNumber();

        Phone phone = new Phone(selectedBrand, selectedModel, serialNumber, selectedStorage, selectedOperatingSystem);
        System.out.println("Phone is created.");
        manager.addPhone(phone);
        return phone;

    }

    private static Phone selectAPhone() {
        Phone phone = null;
        try {
            manager.isPhoneExists();
            do {
                System.out.println("Please select a phone id from the list. ");
                manager.getPhoneDetails();

                Scanner s = new Scanner(System.in);
                int selectedPhoneId = s.nextInt();

                phone = manager.selectAPhone(selectedPhoneId);
                return phone;

            } while (phone == null);

        } catch (NoPhoneFound noPhoneFound) {
            System.out.println(noPhoneFound.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    private static void installApp() {
        Phone phone = selectAPhone();
        int id = -1;
        if (phone == null) {
            return;
        }
        do {
            System.out.println("Please select an app id to install your phone. ");
            manager.getApps().forEach(System.out::println);
            Scanner s = new Scanner(System.in);
            id = s.nextInt();
            manager.installApps(phone, id);
        }

        while (!manager.isAppExists(id));

    }

    private static void removeInstalledApp() {
        Phone phone = selectAPhone();
        int id;
        if (phone == null) {
            return;
        }
        do {
            if (phone.getAppList().isEmpty()) {
                System.out.println("There is no application installed.");
                break;
            }
            System.out.println("Please select an app id to uninstall from your phone. ");
            manager.getApps().forEach(System.out::println);
            Scanner s = new Scanner(System.in);
            id = s.nextInt();
            manager.removeInstalledApp(phone, id);
        } while (!manager.isAppExists(id));

    }

    private static void printInstalledApp() {
        Phone phone = selectAPhone();
        if (phone == null) {
            return;
        }
        phone.getAppList().forEach(System.out::println);
    }

    private static void addContact() {
        Phone phone = selectAPhone();
        if (phone == null) {
            return;
        }
        Scanner s = new Scanner(System.in);
        System.out.println("Contact name: ");
        String name = s.nextLine();

        System.out.println("Contact surname: ");
        String surname = s.nextLine();

        System.out.println("Contact phone: ");
        String mobilePhone = s.nextLine();

        System.out.println("Contact email: ");
        String email = s.nextLine();

        Contacts contact = new Contacts(name, surname, mobilePhone, email);

        manager.addContact(phone, contact);

    }

    private static void updateContactNumber() {
        Phone phone = selectAPhone();
        if (phone == null) {
            return;
        }
        String number;
        int id;

        do {
            System.out.println("Please select a contact to update phone number from the contact list. ");
            phone.getContactList().forEach(System.out::println);
            Scanner s = new Scanner(System.in);
            id = s.nextInt();
            System.out.println("Please enter new phone number. ");
            number = s.next();

        } while (!manager.isContactExists(phone, id));

        manager.updateContactNumber(phone, id, number);

    }

    private static void removeContact() {
        Phone phone = selectAPhone();
        int id = -1;
        if (phone == null) {
            return;
        }
        do {
            if (phone.getContactList().isEmpty()) {
                System.out.println("No contacts found. ");
                break;
            }
            System.out.println("Please select a contact to delete from the contact list. ");
            phone.getContactList().forEach(System.out::println);
            Scanner s = new Scanner(System.in);
            id = s.nextInt();

        } while (!manager.isContactExists(phone, id));

        manager.removeContact(phone, id);
    }

    private static void printAddedContacts() {
        Phone phone = selectAPhone();
        if (phone == null) {
            return;
        }
        phone.getContactList().forEach(System.out::println);
    }

    private static void getPhoneStorage() {
        Phone phone = selectAPhone();
        if (phone == null) {
            return;
        }
        System.out.println("Storage size of the phone is: " + phone.getStorage());
    }

    private static void getPhoneRemainedStorage() {
        Phone phone = selectAPhone();
        if (phone == null) {
            return;
        }
        System.out.println("Remained size of the phone is: " + manager.getPhoneRemainedStorage(phone));
    }

    public static void getInstalledAppsBySize() {
        Phone phone = selectAPhone();
        if (phone == null) {
            return;
        }
        System.out.println(manager.getInstalledAppsBySize(phone));

    }

    public static void writeFile(){
        Phone phone = selectAPhone();
        PhoneBackup pb = new PhoneBackup();
        pb.writeFile(phone);
    }

    public static void backupPhone(){
        Phone phone = selectAPhone();
        PhoneBackup pb = new PhoneBackup();
        pb.backupPhone(phone);
    }

}
