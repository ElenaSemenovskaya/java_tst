package se.tst.addressbook.tests;


import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import se.tst.addressbook.model.GroupDate;
import se.tst.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class DeleteGroupTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions () {
    if (app.db().groups().size() == 0) {
      app.goTo().GroupPage();
      app.group().create(new GroupDate().withName("tst4"));
    }
  }

  @Test
  public void testDeleteGroup() throws Exception {
    Groups before = app.db().groups();
    GroupDate deletedGroup = before.iterator().next();
    app.goTo().GroupPage();
    app.group().delete(deletedGroup);
    assertEquals(app.group().count(), before.size()-1);
    Groups after = app.db().groups();
    assertThat (after, CoreMatchers.equalTo(before.without(deletedGroup)));

  }

}
