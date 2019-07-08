package se.tst.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import se.tst.addressbook.model.ContactDate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "GroupCount")
    public int count;

    @Parameter (names = "-f", description = "Target file")
    public String file;

    public static void main (String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();

    }

    private void run() throws IOException {
        List<ContactDate> contacts = generateContacts(count);
        save(contacts, new File(file));
    }

    private void save(List<ContactDate> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file);
        for (ContactDate contact : contacts){
            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s\n", contact.getName(), contact.getLastname(),
                    contact.getAddress(), contact.getGroup(),
                    contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone()));
        }
        writer.close();
    }

    private List<ContactDate> generateContacts(int count) {
        List<ContactDate> contacts = new ArrayList<ContactDate>();
        for (int i = 0; i < count; i++)
            contacts.add(new ContactDate().withName(String.format("Name %s", i)).withLastname(String.format("Lastame %s", i))
                    .withAddress(String.format("Address %s", i)).withGroup(String.format("Group %s", i))
                    .withHomePhone(String.format("HomePhone %s", i)).withMobilePhone(String.format("MobilePhone %s", i)).withWorkPhone(String.format("WorkPhone %s", i)));
            return contacts;
    }
}
