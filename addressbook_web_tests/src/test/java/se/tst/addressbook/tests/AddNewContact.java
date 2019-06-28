package se.tst.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import se.tst.addressbook.model.ContactDate;

import java.util.Comparator;
import java.util.List;

public class AddNewContact extends TestBase{


  @Test
  public void testAddNewContact() throws Exception {
    List<ContactDate> before = app.contact().list();
    app.goTo().contactList();
    ContactDate contact = new ContactDate("Name1", "LastName1", "tst10", "Address", "tlf1", "mail@mail");
    app.contact().create(contact, true);
    List<ContactDate> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() +1);

    before.add(contact);
    Comparator<? super ContactDate> byId = ((c1, c2) -> Integer.compare(c1.getId(), c2.getId()));
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);

  }


}
