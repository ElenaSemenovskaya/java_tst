package se.tst.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import se.tst.addressbook.model.ContactDate;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactMailTest extends TestBase {

    @BeforeMethod
    public void ensurePrecondition () {
        app.goTo().scrollContact();
        if (app.contact().list().size() == 0) {
            app.goTo().contactList();
            app.contact().create(new ContactDate().
                    withName("Name1").withLastname("LastName1").withGroup("tst10").withAddress("Address").withHomePhone("4444").withMail("mail@mail"), true);
            app.goTo().scrollContact();
        }
    }

    @Test
    public void testContactMail (){
        app.goTo().scrollContact();
        ContactDate contact = app.contact().all().iterator().next();
        ContactDate contactInfoFromEditForm = app.contact().infoFormEditForm(contact);
        assertThat(contact.getAllMail(), equalTo(mergeMail(contactInfoFromEditForm)));

    }

    private String mergeMail(ContactDate contact) {
        return Arrays.asList(contact.getMail(), contact.getMail2(), contact.getMail3())
                .stream().collect(Collectors.joining());
    }


}
