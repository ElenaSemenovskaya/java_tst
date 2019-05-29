package se.tst.addressbook;


import org.testng.annotations.*;


public class GroupCreationTest extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {
    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupDate("tst4", "tst5", "tst6"));
    submitGroupCreation();
    returnToGroupPage();
    logout();
  }


}
