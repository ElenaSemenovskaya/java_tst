package se.tst.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import se.tst.addressbook.model.ContactDate;

import java.util.HashSet;
import java.util.List;

public class AddNewContact extends TestBase{


  @Test
  public void testAddNewContact() throws Exception {
    List<ContactDate> before = app.getContactHelper().getContactList();
    app.getNavigationHelper().gotoContactList();
    ContactDate contact = new ContactDate("Name1", "LastName1", "tst10", "Address", "tlf1", "mail@mail");
    app.getContactHelper().createContact(contact, true);
    List<ContactDate> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() +1);

    before.add(contact);
    int max = 0;
    for (ContactDate c : after){
      if (c.getId() > max) {
        max = c.getId();
      }
    }
    contact.setId(max);
    Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));
    app.logout();
  }


}
