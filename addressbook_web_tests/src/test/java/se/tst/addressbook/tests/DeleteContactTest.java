package se.tst.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import se.tst.addressbook.model.ContactDate;
import se.tst.addressbook.model.Contacts;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
    Contacts before = app.contact().all();
    ContactDate deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().scrollContact();
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size() -1);
    assertThat(after, equalTo(before.without(deletedContact)));

  }



}
