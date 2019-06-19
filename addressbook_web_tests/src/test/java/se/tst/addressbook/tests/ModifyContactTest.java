package se.tst.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import se.tst.addressbook.model.ContactDate;

import java.util.HashSet;
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
  ContactDate contact = new ContactDate("Name1", "LastName1", null, "Address1", null, null);
  app.getContactHelper().fillContactForm(contact, false);
  app.getContactHelper().updateContact();
  app.getContactHelper().gotoHomePage();
  List<ContactDate> after = app.getContactHelper().getContactList();
  Assert.assertEquals(after.size(), before.size());


  before.remove(before.size()-1);
  before.add(contact);
  Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));
  app.logout();

}
}
