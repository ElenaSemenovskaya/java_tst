package se.tst.addressbook.tests;

import org.testng.annotations.Test;
import se.tst.addressbook.model.ContactDate;
import se.tst.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddNewContact extends TestBase{


  @Test
  public void testAddNewContact() throws Exception {
    Contacts before = app.contact().all();
    app.goTo().contactList();
    ContactDate contact = new ContactDate().
            withName("Name1").withLastname("LastName1").withGroup("tst10").withAddress("Address").withTlfn("tlf1").withMail("mail@mail");
    app.contact().create(contact, true);
    assertThat(app.contact().count(), equalTo(before.size() +1));
    Contacts after = app.contact().all();

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

  }


}
