package se.tst.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import se.tst.addressbook.model.ContactDate;
import se.tst.addressbook.model.Groups;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "ContactCount")
    public int count;

    @Parameter (names = "-f", description = "Target file")
    public String file;

    @Parameter (names = "-d", description = "Date Format")
    public String format;

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
        if (format.equals("csv")) {
            saveAsCsv(contacts, new File(file));
        } else if (format.equals("xml")){
            saveAsXml(contacts, new File(file));
        } else {
            System.out.println("Нераспознанный формат " + format    );
        }
    }

    private void saveAsXml(List<ContactDate> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactDate.class);
        String xml = xstream.toXML(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }

    private void saveAsCsv(List<ContactDate> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        try (Writer writer = new FileWriter(file)) {
            for (ContactDate contact : contacts){
                writer.write(String.format("%s;%s;%s;%s;%s;%s;s;%s;%s;%s\n"
                        , contact.getName()
                        , contact.getLastname()
                        , contact.getPhoto()
                        , contact.getAddress()
                        , contact.getHomePhone()
                        , contact.getMobilePhone()
                        , contact.getWorkPhone()
                        , contact.getMail()
                        , contact.getMail2()
                        , contact.getMail3()));
            }
        }
    }

    private List<ContactDate> generateContacts(int count) {
        List<ContactDate> contacts = new ArrayList<ContactDate>();
        for (int i = 0; i < count; i++)
            contacts.add(new ContactDate()
                    .withName(String.format("Name %s", i))
                    .withLastname(String.format("Lastname %s", i))
                    .withPhoto(new File("src/test/resources/fix.png"))
                    .withAddress(String.format("Address %s", i))
                    .withHomePhone(String.format("HomePhone %s", i))
                    .withMobilePhone(String.format("MobilePhone %s", i))
                    .withWorkPhone(String.format("WorkPhone %s", i))
                    .withMail(String.format("mail %s", i))
                    .withMail2(String.format("2mail %s", i))
                    .withMail3(String.format("3mail %s", i)));

            return contacts;
    }
}
