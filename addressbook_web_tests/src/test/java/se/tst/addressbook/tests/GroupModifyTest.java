package se.tst.addressbook.tests;

import org.testng.annotations.Test;
import se.tst.addressbook.model.GroupDate;

public class GroupModifyTest extends TestBase {

  @Test
  public void testGroupModify () {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModify();
    app.getGroupHelper().fillGroupForm(new GroupDate("tst10", null, null));
    app.getGroupHelper().submitGroupModify();
    app.getGroupHelper().returnToGroupPage();
    app.logout();
  }
}
