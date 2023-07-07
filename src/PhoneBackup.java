import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PhoneBackup {

    private static Manager manager;
    public void writeFile(Phone phone){

        if (phone == null) {
            return;
        }

        ArrayList<String> contactList = new ArrayList<>(List.of(phone.getContactList().toString()));
        ArrayList<String> appList = new ArrayList<>(List.of(phone.getAppList().toString()));

        try (FileOutputStream f = new FileOutputStream("backupFile");
             ObjectOutputStream o = new ObjectOutputStream(f);) {

            o.writeObject(contactList);
            o.writeObject(appList);

        } catch (FileNotFoundException e) {
            System.out.println("File not found: ");

            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Error while writing data:");

            throw new RuntimeException(e);
        }
    }

    public void backupPhone(Phone phone){

        ArrayList<String> contactList = null;
        ArrayList<String> appList = null;

        try (FileInputStream f = new FileInputStream("backupFile");
             ObjectInputStream o = new ObjectInputStream(f);) {
            contactList = (ArrayList) o.readObject();
            appList = (ArrayList) o.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
        }

        for (String name : contactList) {
            System.out.println(name);
        }

        for (String name : appList) {
            System.out.println(name);
        }


    }
}
