package se.tst.addressbook.tests;

import org.testng.annotations.Test;
import se.tst.addressbook.model.ContactDate;

public class AddNewContact extends TestBase{


  @Test
  public void testAddNewContact() throws Exception {
    app.gotoContactList();
    app.initContactCreation();
    app.fillContactForm(new ContactDate("Name1", "LastName1", "Address", "tlf1", "mail@mail"));
    app.submit();
    app.logout();
  }


}
