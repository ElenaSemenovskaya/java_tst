package se.tst.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import se.tst.addressbook.model.ContactDate;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class AddNewContact extends TestBase{


  @Test
  public void testAddNewContact() throws Exception {
    Set<ContactDate> before = app.contact().all();
    app.goTo().contactList();
    ContactDate contact = new ContactDate().
            withName("Name1").withLastname("LastName1").withGroup("tst10").withAddress("Address").withTlfn("tlf1").withMail("mail@mail");
    app.contact().create(contact, true);
    Set<ContactDate> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() +1);

    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(after, before);

  }


}
