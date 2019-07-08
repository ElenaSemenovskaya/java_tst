package se.tst.addressbook.tests;

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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddNewContact extends TestBase{

  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    //List<File> photo = new ArrayList<File>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
    String line = reader.readLine();
    while (line != null) {
      String[] split = line.split(";");
      list.add(new Object[]{(new ContactDate()
              .withName(split[0])
              .withLastname(split[1])
              .withAddress(split[2])
              .withGroup(split[3])
              .withHomePhone(split[4])
              .withMobilePhone(split[5])
              .withWorkPhone(split[6]))});
      line = reader.readLine();
    }
    return list.iterator();
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
