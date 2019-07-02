package se.tst.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import se.tst.addressbook.model.ContactDate;
import se.tst.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
  Contacts before = app.contact().all();
  ContactDate modifiedContact = before.iterator().next();
  ContactDate contact = new ContactDate().
          withId(modifiedContact.getId()).withName("Name1").withLastname("LastName1").withGroup("tst10").withAddress("Address").withTlfn("tlf1").withMail("mail@mail");
  app.contact().modify(contact);
  assertEquals(app.contact().count(), before.size());
  Contacts after = app.contact().all();
  assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));

}


}
