package se.tst.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import se.tst.addressbook.model.GroupDate;

import java.util.List;

public class GroupModifyTest extends TestBase {

  @Test
  public void testGroupModify () {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupDate("tst4", "tst5", null));
    }
    List<GroupDate> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size()-3);
    app.getGroupHelper().initGroupModify();
    app.getGroupHelper().fillGroupForm(new GroupDate("tst10", null, null));
    app.getGroupHelper().submitGroupModify();
    app.getGroupHelper().returnToGroupPage();
    List<GroupDate> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size());
    app.logout();
  }
}
