package se.tst.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import se.tst.addressbook.model.GroupDate;

public class GroupModifyTest extends TestBase {

  @Test
  public void testGroupModify () {
    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupHelper().getCountGroup();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupDate("tst4", "tst5", null));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModify();
    app.getGroupHelper().fillGroupForm(new GroupDate("tst10", null, null));
    app.getGroupHelper().submitGroupModify();
    app.getGroupHelper().returnToGroupPage();
    int after = app.getGroupHelper().getCountGroup();
    Assert.assertEquals(after, before);
    app.logout();
  }
}
