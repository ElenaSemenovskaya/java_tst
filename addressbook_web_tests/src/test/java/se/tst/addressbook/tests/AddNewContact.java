package se.tst.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import se.tst.addressbook.model.ContactDate;
import se.tst.addressbook.model.Contacts;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddNewContact extends TestBase{

  @DataProvider
  public Iterator<Object[]> validContacts() {
    List<Object[]> list = new ArrayList<Object[]>();
    List<File> photo = new ArrayList<File>();
    list.add(new Object[] {new ContactDate().withName("Name1").withLastname("LastName1")
            .withPhoto(new File("src/test/resources/fix.png")).withGroup("tst10").withAddress("Address1")
            .withHomePhone("1111").withMail("mail1@mail")});
    list.add(new Object[] {new ContactDate().withName("Name2").withLastname("LastName2")
            .withPhoto(new File("src/test/resources/sim.png")).withGroup("tst10").withAddress("Address2")
            .withHomePhone("1111").withMail("mail2@mail")});
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
