package se.tst.addressbook.tests;

import org.testng.annotations.Test;
import se.tst.addressbook.model.ContactDate;

public class ModifyContactTest extends TestBase {

@Test

  public void testModifyContact () {
  app.getNavigationHelper().gotoScrollContact();
  app.getContactHelper().editConact();
  app.getContactHelper().fillContactForm(new ContactDate("Name1", "LastName1", "tst10", "Address", "tlf1", null), false);
  app.getContactHelper().updateContact();
  app.logout();

}
}
