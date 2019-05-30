package se.tst.addressbook.tests;


import org.testng.annotations.Test;


public class DeleteGroupTest extends TestBase {


  @Test
  public void testDeleteGroup() throws Exception {
    app.gotoGroupPage();
    app.selectGroup();
    app.deleteSelectedGroups();
    app.returnToGroupPage();
    app.logout();
  }


}
