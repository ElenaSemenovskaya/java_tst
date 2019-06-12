package se.tst.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import se.tst.addressbook.model.ContactDate;

public class AddNewContact extends TestBase{


  @Test
  public void testAddNewContact() throws Exception {
    int before = app.getContactHelper().getCountContact();
    app.getNavigationHelper().gotoContactList();
    app.getContactHelper().createContact(new ContactDate("Name1", "LastName1", "tst10", "Address", "tlf1", "mail@mail"), true);
    int after = app.getContactHelper().getCountContact();
    Assert.assertEquals(after, before +1);
    app.logout();
  }


}
