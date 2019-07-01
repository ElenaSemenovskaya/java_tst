package se.tst.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import se.tst.addressbook.model.ContactDate;

import java.util.Set;

public class ModifyContactTest extends TestBase {

  @BeforeMethod
  public void ensurePrecondition () {
    app.goTo().scrollContact();
    if (app.contact().list().size() == 0) {
      app.goTo().contactList();
      app.contact().create(new ContactDate().
              withName("Name1").withLastname("LastName1").withGroup("tst10").withAddress("Address").withTlfn("tlf1").withMail("mail@mail"), true);
      app.goTo().scrollContact();
    }
  }
@Test

  public void testModifyContact () {
  Set<ContactDate> before = app.contact().all();
  ContactDate modifiedContact = before.iterator().next();
  ContactDate contact = new ContactDate().
          withId(modifiedContact.getId()).withName("Name1").withLastname("LastName1").withGroup("tst10").withAddress("Address").withTlfn("tlf1").withMail("mail@mail");
  app.contact().modify(contact);
  Set<ContactDate> after = app.contact().all();
  Assert.assertEquals(after.size(), before.size());
  before.remove(modifiedContact);
  before.add(contact);
  Assert.assertEquals(after, before);

}


}
