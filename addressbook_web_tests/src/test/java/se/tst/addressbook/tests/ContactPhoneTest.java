package se.tst.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import se.tst.addressbook.model.ContactDate;


public class ContactPhoneTest extends TestBase{

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
    public void testContactPhones (){
        app.goTo().scrollContact();
        ContactDate contact = app.contact().all().iterator().next();
        ContactDate contactInfoFromEditForm = app.contact().infoFormEditForm(contact);

    }

}
