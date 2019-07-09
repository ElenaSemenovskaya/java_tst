package se.tst.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import se.tst.addressbook.model.ContactDate;
import se.tst.addressbook.model.Contacts;
import se.tst.addressbook.model.GroupDate;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddNewContact extends TestBase{

  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    //List<File> photo = new ArrayList<File>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));
    String xml = "";
    String line = reader.readLine();
    while (line != null) {
      xml += line;
      line = reader.readLine();
    }
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactDate.class);
    List<ContactDate> contacts = (List<ContactDate>) xstream.fromXML (xml);
    return contacts.stream().map((c)-> new Object[]{c}).collect(Collectors.toList()).iterator();
  }


  @Test (dataProvider = "validContacts")
  public void testAddNewContact(ContactDate contact) throws Exception {
    Contacts before = app.contact().all();
    app.goTo().contactList();
    app.contact().create(contact, true);

    assertThat(app.contact().count(), equalTo(before.size() +1));
    Contacts after = app.contact().all();

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

  }


}
