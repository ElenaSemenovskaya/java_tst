package se.tst.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import se.tst.addressbook.model.ContactDate;

public class ModifyContactTest extends TestBase {

@Test

  public void testModifyContact () {
  app.getNavigationHelper().gotoScrollContact();
  if (! app.getContactHelper().isThereAContact()) {
    app.getNavigationHelper().gotoContactList();
    app.getContactHelper().createContact(new ContactDate("Name", "LastName", "tst10", "Address", "tlf", "mail@mail"), true);
    app.getNavigationHelper().gotoScrollContact();
  }
  int before = app.getContactHelper().getCountContact();
  app.getContactHelper().editConact();
  app.getContactHelper().fillContactForm(new ContactDate("Name1", "LastName1", "tst10", "Address1", "tlf1", null), false);
  app.getContactHelper().updateContact();
  app.getContactHelper().gotoHomePage();
  int after = app.getGroupHelper().getCountGroup();
  Assert.assertEquals(after, before);
  app.logout();

}
}
