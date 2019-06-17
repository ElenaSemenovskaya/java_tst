package se.tst.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import se.tst.addressbook.model.GroupDate;

import java.util.HashSet;
import java.util.List;

public class GroupModifyTest extends TestBase {

  @Test
  public void testGroupModify () {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupDate("tst4", "tst5", null));
    }
    List<GroupDate> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size()-1);
    app.getGroupHelper().initGroupModify();
    GroupDate group = new GroupDate(before.get(before.size()-1).getId(),"tst10", null, null);
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupModify();
    app.getGroupHelper().returnToGroupPage();
    List<GroupDate> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size()-1);
    before.add(group);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    app.logout();
  }
}
