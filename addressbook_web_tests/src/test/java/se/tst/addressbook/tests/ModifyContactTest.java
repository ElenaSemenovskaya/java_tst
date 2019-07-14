package se.tst.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import se.tst.addressbook.model.ContactDate;
import se.tst.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ModifyContactTest extends TestBase {

  @BeforeMethod
  public void ensurePrecondition () {
    app.goTo().scrollContact();
    if (app.db().contacts().size() == 0) {
      app.goTo().contactList();
      app.contact().create(new ContactDate().
              withName("Name1").withLastname("LastName1").withPhoto(new File("src/test/resources/fix.png")).withGroup("tst10").withAddress("Address").withHomePhone("4444").withMail("mail@mail"), true);
      app.goTo().scrollContact();
    }
  }
@Test

  public void testModifyContact () {
  Contacts before = app.db().contacts();
  ContactDate modifiedContact = before.iterator().next();
  ContactDate contact = new ContactDate().
          withId(modifiedContact.getId())
          .withName("Name1")
          .withLastname("LastName1")
          .withPhoto(new File("src/test/resources/fix.png"))
          .withAddress("Address")
          .withHomePhone("5555")
          .withMobilePhone("7777")
          .withWorkPhone("3333")
          .withMail("mail@mail")
          .withMail2("mail2")
          .withMail3("mail3");
  app.contact().modify(contact);
  Contacts after = app.db().contacts();
  assertEquals(app.contact().count(), before.size());

  //Contacts expected = before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()));
  //assertThat(after, equalTo(expected));

  assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  veryfiContactListInUI();

}


}
