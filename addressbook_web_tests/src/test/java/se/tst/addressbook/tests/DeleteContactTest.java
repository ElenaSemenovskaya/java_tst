package se.tst.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import se.tst.addressbook.model.ContactDate;

import java.util.List;

public class DeleteContactTest extends TestBase{

  @BeforeMethod

  public void ensurePrecondition () {
    app.goTo().scrollContact();
    if (app.contact().list().size() == 0) {
      app.goTo().contactList();
      app.contact().create(new ContactDate("Name1", "LastName1", "tst10", "Address", "tlf1", "mail@mail"), true);
      app.goTo().scrollContact();
    }
  }

  @Test

  public void testDeleteContact () {
    List<ContactDate> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().delete(index);
    app.goTo().scrollContact();
    List<ContactDate> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() -1);
    before.remove(index);
    Assert.assertEquals(before, after);

  }



}
