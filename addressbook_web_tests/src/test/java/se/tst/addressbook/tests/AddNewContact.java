package se.tst.addressbook.tests;

import org.testng.annotations.Test;
import se.tst.addressbook.model.ContactDate;

public class AddNewContact extends TestBase{


  @Test
  public void testAddNewContact() throws Exception {
    app.getNavigationHelper().gotoContactList();
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactDate("Name1", "LastName1", "tst10", "Address", "tlf1", "mail@mail"));
    app.getContactHelper().submit();
    app.logout();
  }


}
