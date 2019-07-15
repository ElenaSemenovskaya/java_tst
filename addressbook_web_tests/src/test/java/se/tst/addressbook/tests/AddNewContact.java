package se.tst.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import se.tst.addressbook.model.ContactDate;
import se.tst.addressbook.model.Contacts;
import se.tst.addressbook.model.GroupDate;
import se.tst.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddNewContact extends TestBase{

  @BeforeTest
  public void ensurePreconditions () {
    if (app.db().groups().size() == 0) {
      app.goTo().GroupPage();
      app.group().create(new GroupDate().withName("tst4"));
    }
  }


  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(ContactDate.class);
      List<ContactDate> contacts = (List<ContactDate>) xstream.fromXML (xml);
      return contacts.stream().map((c)-> new Object[]{c}).collect(Collectors.toList()).iterator();
    }
  }

  @Test (dataProvider = "validContacts")
  public void testAddNewContact(ContactDate contact) throws Exception {
    Groups groups = app.db().groups();
    Contacts before = app.db().contacts();
    contact.withPhoto(new File("src/test/resources/fix.png")).inGroup(groups.iterator().next());

    app.goTo().contactList();
    app.contact().create(contact, true);
    Contacts after = app.db().contacts();
    assertThat(app.contact().count(), equalTo(before.size() +1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    veryfiContactListInUI();
  }


}
