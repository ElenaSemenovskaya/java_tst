package se.tst.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import se.tst.addressbook.model.ContactDate;

import java.util.List;
import java.util.Set;

public class DeleteContactTest extends TestBase{

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

  public void testDeleteContact () {
    Set<ContactDate> before = app.contact().all();
    ContactDate deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().scrollContact();
    Set<ContactDate> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() -1);
    before.remove(deletedContact);
    Assert.assertEquals(before, after);

  }



}
