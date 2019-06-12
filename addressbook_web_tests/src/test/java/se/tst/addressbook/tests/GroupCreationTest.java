package se.tst.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.*;
import se.tst.addressbook.model.GroupDate;


public class GroupCreationTest extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupHelper().getCountGroup();
    app.getGroupHelper().createGroup(new GroupDate("tst4", "tst5", null));
    int after = app.getGroupHelper().getCountGroup();
    Assert.assertEquals(after, before + 1);
    app.logout();
  }


}
