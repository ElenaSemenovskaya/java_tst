package se.tst.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import se.tst.addressbook.model.ContactDate;
import se.tst.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class DeleteContactTest extends TestBase{

  @BeforeMethod

  public void ensurePrecondition () {
    app.goTo().scrollContact();
    if (app.db().contacts().size() == 0) {
      app.goTo().contactList();
      app.contact().create(new ContactDate().
              withName("Name1").withLastname("LastName1").withAddress("Address").withHomePhone("2222").withMail("mail@mail"), true);
      app.goTo().scrollContact();
    }
  }

  @Test

  public void testDeleteContact () {
    Contacts before = app.db().contacts();
    ContactDate deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().scrollContact();
    assertEquals(app.contact().count(), before.size() -1);
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(deletedContact)));

  }



}
