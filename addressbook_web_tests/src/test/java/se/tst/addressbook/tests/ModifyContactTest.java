package se.tst.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import se.tst.addressbook.model.ContactDate;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ModifyContactTest extends TestBase {

@Test (enabled = false)

  public void testModifyContact () {
  app.getNavigationHelper().gotoScrollContact();
  if (! app.getContactHelper().isThereAContact()) {
    app.getNavigationHelper().gotoContactList();
    app.getContactHelper().createContact(new ContactDate("Name", "LastName", "tst10", "Address", "tlf", "mail@mail"), true);
    app.getNavigationHelper().gotoScrollContact();
  }
  List<ContactDate> before = app.getContactHelper().getContactList();
  app.getContactHelper().editConact(before.size()-1);
  ContactDate contact = new ContactDate(before.get(before.size()-1).getId(), "Name1", "LastName1", null, "Address1", null, null);
  app.getContactHelper().fillContactForm(contact, false);
  app.getContactHelper().updateContact();
  app.getContactHelper().gotoHomePage();
  List<ContactDate> after = app.getContactHelper().getContactList();
  Assert.assertEquals(after.size(), before.size());
  before.remove(before.size()-1);
  before.add(contact);
  Comparator<? super ContactDate> byId = ((c1, c2) -> Integer.compare(c1.getId(), c2.getId()));
  before.sort(byId);
  after.sort(byId);
  Assert.assertEquals(after, before);
  app.logout();

}
}
