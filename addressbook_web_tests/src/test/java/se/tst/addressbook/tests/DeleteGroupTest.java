package se.tst.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import se.tst.addressbook.model.GroupDate;

import java.util.List;


public class DeleteGroupTest extends TestBase {


  @Test
  public void testDeleteGroup() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupDate("tst4", "tst5", null));
    }
    List<GroupDate> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
    List<GroupDate> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() - 1);
    app.logout();
  }


}
