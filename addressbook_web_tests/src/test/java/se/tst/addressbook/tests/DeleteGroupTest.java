package se.tst.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import se.tst.addressbook.model.GroupDate;

import java.util.List;


public class DeleteGroupTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions () {
    app.goTo().GroupPage();
    if (app.group().list().size() == 0) {
      app.group().create(new GroupDate().withName("tst4"));
    }
  }

  @Test
  public void testDeleteGroup() throws Exception {
    List<GroupDate> before = app.group().list();
    int index = before.size() - 1;
    app.group().delete(index);
    List<GroupDate> after = app.group().list();
    Assert.assertEquals(after.size(), index);
    before.remove(index);
    Assert.assertEquals(before, after);

  }

}
