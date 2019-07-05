package se.tst.addressbook.generators;

import se.tst.addressbook.model.ContactDate;
import se.tst.addressbook.model.GroupDate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    public static void main (String[] args) throws IOException {
        int count = Integer.parseInt(args[0]);
        File file = new File(args[1]);

        List<ContactDate> contacts = generateContacts(count);
        save(contacts, file);
    }

    private static void save(List<ContactDate> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file);
        for (ContactDate contact : contacts){
            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s\n", contact.getName(), contact.getLastname(),
                    contact.getAddress(), contact.getGroup(),
                    contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone()));
        }
        writer.close();
    }

    private static List<ContactDate> generateContacts(int count) {
        List<ContactDate> contacts = new ArrayList<ContactDate>();
        for (int i = 0; i < count; i++)
            contacts.add(new ContactDate().withName(String.format("Name %s", i)).withLastname(String.format("Lastame %s", i))
                    .withAddress(String.format("Address %s", i)).withGroup(String.format("Group %s", i))
                    .withHomePhone(String.format("HomePhone %s", i)).withMobilePhone(String.format("MobilePhone %s", i)).withWorkPhone(String.format("WorkPhone %s", i)));
            return contacts;
    }
}
