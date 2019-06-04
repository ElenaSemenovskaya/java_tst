package se.tst.addressbook.tests;

import org.testng.annotations.Test;

public class DeleteContactTest extends TestBase{

  @Test

  public void testDeleteContact () {
    app.getNavigationHelper().gotoScrollContact();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteContact();
    app.getContactHelper().assertDeleteContact();
    app.logout();

  }

}
