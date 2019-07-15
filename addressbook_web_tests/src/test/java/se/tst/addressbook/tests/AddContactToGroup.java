package se.tst.addressbook.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import se.tst.addressbook.model.ContactDate;
import se.tst.addressbook.model.GroupDate;
import se.tst.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

public class AddContactToGroup  extends TestBase{

    @BeforeTest
    public void ensurePrecondition () {
        Groups groups = app.db().groups();
        app.goTo().scrollContact();
        if (app.db().groups().size() == 0) {
            app.goTo().GroupPage();
            app.group().create(new GroupDate().withName("tst4"));
        }
        if (app.db().contacts().size() == 0) {
            app.goTo().contactList();
            app.contact().create(new ContactDate()
                    .withName("Name1")
                    .withLastname("LastName1")
                    .withPhoto(new File("src/test/resources/fix.png"))
                    .withAddress("Address")
                    .withHomePhone("5555")
                    .withMobilePhone("7777")
                    .withWorkPhone("3333")
                    .withMail("mail@mail")
                    .withMail2("mail2")
                    .withMail3("mail3")
                    .inGroup(groups.iterator().next()), true);
            app.goTo().scrollContact();
        }
    }

    @Test
    public void testAddContactToGroup () {
        app.goTo().scrollContact();
        ContactDate beforeContactToAddGroup = app.db().contacts().iterator().next();
        GroupDate beforeGroupToAdd= app.db().groups().iterator().next();
        app.contact().addContactToGroup(beforeContactToAddGroup.inGroup(beforeGroupToAdd));
        app.db().updateContact(beforeContactToAddGroup);
        app.db().updateGroup(beforeGroupToAdd);
        ContactDate afterContactToAddGroup = app.db().contacts().iterator().next();
        GroupDate afterGroupToAdd= app.db().groups().iterator().next();
        assertThat(afterContactToAddGroup.getGroups(), hasItem(beforeGroupToAdd));
        assertThat(afterGroupToAdd.getContacts(), hasItem(beforeContactToAddGroup));
    }
}
