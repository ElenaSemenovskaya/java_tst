package se.tst.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import se.tst.addressbook.model.ContactDate;

import java.util.Comparator;
import java.util.List;

public class ModifyContactTest extends TestBase {

  @BeforeMethod
  public void ensurePrecondition () {
    app.goTo().gotoScrollContact();
    if (! app.getContactHelper().isThereAContact()) {
      app.goTo().gotoContactList();
      app.getContactHelper().createContact(new ContactDate("Name", "LastName", "tst10", "Address", "tlf", "mail@mail"), true);
      app.goTo().gotoScrollContact();
    }
  }
@Test

  public void testModifyContact () {
  List<ContactDate> before = app.getContactHelper().getContactList();
  int index = before.size()-1;
  ContactDate contact = new ContactDate(before.get(index).getId(), "Name1", "LastName1", null, "Address1", null, null);
  app.getContactHelper().modifyContact(index, contact);
  List<ContactDate> after = app.getContactHelper().getContactList();
  Assert.assertEquals(after.size(), before.size());
  before.remove(index);
  before.add(contact);
  Comparator<? super ContactDate> byId = ((c1, c2) -> Integer.compare(c1.getId(), c2.getId()));
  before.sort(byId);
  after.sort(byId);
  Assert.assertEquals(after, before);

}


}
