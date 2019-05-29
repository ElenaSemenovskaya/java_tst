package se.tst.addressbook;


import org.testng.annotations.*;


public class DeleteGroupTest extends TestBase {


  @Test
  public void testDeleteGroup() throws Exception {
    gotoGroupPage();
    selectGroup();
    deleteSelectedGroups();
    returnToGroupPage();
    logout();
  }


}
