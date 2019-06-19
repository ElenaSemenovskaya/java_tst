package se.tst.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import se.tst.addressbook.model.ContactDate;

import java.util.List;

public class AddNewContact extends TestBase{


  @Test
  public void testAddNewContact() throws Exception {
    List<ContactDate> before = app.getContactHelper().getContactList();
    app.getNavigationHelper().gotoContactList();
    app.getContactHelper().createContact(new ContactDate("Name1", "LastName1", "tst10", "Address", "tlf1", "mail@mail"), true);
    List<ContactDate> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() +1);
    app.logout();
  }


}
