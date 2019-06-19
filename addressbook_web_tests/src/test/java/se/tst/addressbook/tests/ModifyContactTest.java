package se.tst.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import se.tst.addressbook.model.ContactDate;

import java.util.List;

public class ModifyContactTest extends TestBase {

@Test

  public void testModifyContact () {
  app.getNavigationHelper().gotoScrollContact();
  if (! app.getContactHelper().isThereAContact()) {
    app.getNavigationHelper().gotoContactList();
    app.getContactHelper().createContact(new ContactDate("Name", "LastName", "tst10", "Address", "tlf", "mail@mail"), true);
    app.getNavigationHelper().gotoScrollContact();
  }
  List<ContactDate> before = app.getContactHelper().getContactList();
  app.getContactHelper().editConact(before.size()-1);
  app.getContactHelper().fillContactForm(new ContactDate("Name1", "LastName1", "tst10", "Address1", "tlf1", null), false);
  app.getContactHelper().updateContact();
  app.getContactHelper().gotoHomePage();
  List<ContactDate> after = app.getContactHelper().getContactList();
  Assert.assertEquals(after.size(), before.size());
  app.logout();

}
}
