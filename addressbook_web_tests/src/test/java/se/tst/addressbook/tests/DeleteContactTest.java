package se.tst.addressbook.tests;

import org.testng.annotations.Test;
import se.tst.addressbook.model.ContactDate;

public class DeleteContactTest extends TestBase{

  @Test

  public void testDeleteContact () {
    app.getNavigationHelper().gotoScrollContact();
    if (! app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().gotoContactList();
      app.getContactHelper().createContact(new ContactDate("Name1", "LastName1", "tst10", "Address", "tlf1", "mail@mail"), true);
      app.getNavigationHelper().gotoScrollContact();
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteContact();
    app.getContactHelper().assertDeleteContact();
    app.logout();

  }

}
