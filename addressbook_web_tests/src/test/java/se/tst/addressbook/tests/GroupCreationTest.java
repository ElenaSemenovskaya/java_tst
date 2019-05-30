package se.tst.addressbook.tests;


import org.testng.annotations.*;
import se.tst.addressbook.model.GroupDate;


public class GroupCreationTest extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {
    app.gotoGroupPage();
    app.initGroupCreation();
    app.fillGroupForm(new GroupDate("tst4", "tst5", "tst6"));
    app.submitGroupCreation();
    app.returnToGroupPage();
    app.logout();
  }


}
