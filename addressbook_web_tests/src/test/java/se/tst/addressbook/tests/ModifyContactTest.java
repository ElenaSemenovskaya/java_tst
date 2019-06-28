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
  List<ContactDate> before = app.contact().list();
  int index = before.size()-1;
  ContactDate contact = new ContactDate().
          withId(before.get(index).getId()).withName("Name1").withLastname("LastName1").withGroup("tst10").withAddress("Address").withTlfn("tlf1").withMail("mail@mail");
  app.contact().modify(index, contact);
  List<ContactDate> after = app.contact().list();
  Assert.assertEquals(after.size(), before.size());
  before.remove(index);
  before.add(contact);
  Comparator<? super ContactDate> byId = ((c1, c2) -> Integer.compare(c1.getId(), c2.getId()));
  before.sort(byId);
  after.sort(byId);
  Assert.assertEquals(after, before);

}


}
