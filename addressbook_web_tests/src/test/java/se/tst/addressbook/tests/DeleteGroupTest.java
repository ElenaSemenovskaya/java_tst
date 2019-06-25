package se.tst.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import se.tst.addressbook.model.GroupDate;

import java.util.Set;


public class DeleteGroupTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions () {
    app.goTo().GroupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupDate().withName("tst4"));
    }
  }

  @Test
  public void testDeleteGroup() throws Exception {
    Set<GroupDate> before = app.group().all();
    GroupDate deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    Set<GroupDate> after = app.group().all();
    Assert.assertEquals(after.size(), before.size()-1);
    before.remove(deletedGroup);
    Assert.assertEquals(before, after);

  }

}
