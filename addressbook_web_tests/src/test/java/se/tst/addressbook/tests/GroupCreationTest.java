package se.tst.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.*;
import se.tst.addressbook.model.GroupDate;

import java.util.List;


public class GroupCreationTest extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupDate> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().createGroup(new GroupDate("tst4", "tst5", null));
    List<GroupDate> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() + 1);
    app.logout();
  }


}
