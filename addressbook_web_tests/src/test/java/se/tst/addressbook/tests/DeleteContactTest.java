package se.tst.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import se.tst.addressbook.model.ContactDate;

import java.util.List;

public class DeleteContactTest extends TestBase{

  @Test

  public void testDeleteContact () {
    app.getNavigationHelper().gotoScrollContact();

    if (! app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().gotoContactList();
      app.getContactHelper().createContact(new ContactDate("Name1", "LastName1", "tst10", "Address", "tlf1", "mail@mail"), true);
      app.getNavigationHelper().gotoScrollContact();
    }
    List<ContactDate> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteContact();
    app.getContactHelper().assertDeleteContact();
    app.getNavigationHelper().gotoScrollContact();
    List<ContactDate> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() -1);
    app.logout();

  }

}
