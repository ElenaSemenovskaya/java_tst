package se.tst.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import se.tst.addressbook.model.ContactDate;

import java.util.List;

public class DeleteContactTest extends TestBase{

  @BeforeMethod

  public void ensurePrecondition () {
    app.goTo().gotoScrollContact();
    if (! app.getContactHelper().isThereAContact()) {
      app.goTo().gotoContactList();
      app.getContactHelper().createContact(new ContactDate("Name1", "LastName1", "tst10", "Address", "tlf1", "mail@mail"), true);
      app.goTo().gotoScrollContact();
    }
  }

  @Test (enabled = false)

  public void testDeleteContact () {
    List<ContactDate> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteContact();
    app.getContactHelper().assertDeleteContact();
    app.goTo().gotoScrollContact();
    List<ContactDate> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() -1);
    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
    app.logout();

  }

}
