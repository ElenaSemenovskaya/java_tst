package se.tst.addressbook.tests;


import org.testng.annotations.*;
import se.tst.addressbook.model.GroupDate;


public class GroupCreationTest extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().createGroup(new GroupDate("tst4", "tst5", "tst6"));
    app.logout();
  }


}
