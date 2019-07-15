package se.tst.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import se.tst.addressbook.model.ContactDate;
import se.tst.addressbook.model.GroupDate;
import se.tst.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveContactFromGroup extends TestBase{

    @BeforeTest
    public void ensurePrecondition () {
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
                    .withMail3("mail3"), true);
            app.goTo().scrollContact();
        }


    }

    @Test
    public void testRemoveContactFromGroup () {
        app.goTo().scrollContact();
        ContactDate beforeContactRemoveFromGroup = app.db().contacts().iterator().next();
        GroupDate beforeGroup= app.db().groups().iterator().next();
        app.contact().removeContactFromGroup(beforeContactRemoveFromGroup.inGroup(beforeGroup));
        app.db().updateContact(beforeContactRemoveFromGroup);
        app.db().updateGroup(beforeGroup);
        ContactDate afterContactRemoveFromGroup = app.db().contacts().iterator().next();
        GroupDate afterGroup= app.db().groups().iterator().next();
        assertThat(afterContactRemoveFromGroup.getGroups(), CoreMatchers.not(beforeGroup));
        assertThat(afterGroup.getContacts(), CoreMatchers.not(beforeContactRemoveFromGroup));
    }
}
