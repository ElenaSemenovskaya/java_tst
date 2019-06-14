package se.tst.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import se.tst.addressbook.model.GroupDate;


public class DeleteGroupTest extends TestBase {


  @Test
  public void testDeleteGroup() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupHelper().getCountGroup();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupDate("tst4", "tst5", null));
    }
    app.getGroupHelper().selectGroup(before - 1);
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
    int after = app.getGroupHelper().getCountGroup();
    Assert.assertEquals(after, before - 1);
    app.logout();
  }


}
