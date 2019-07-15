package se.tst.addressbook.tests;

import org.testng.annotations.Test;
import se.tst.addressbook.model.ContactDate;
import se.tst.addressbook.model.GroupDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

public class AddContactToGroup  extends TestBase{


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
