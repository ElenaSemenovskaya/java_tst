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
    app.goTo().GroupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupDate().withName("tst4"));
    }
  }

  @Test
  public void testDeleteGroup() throws Exception {
    Groups before = app.group().all();
    GroupDate deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    assertEquals(app.group().count(), before.size()-1);
    Groups after = app.group().all();
    assertThat (after, CoreMatchers.equalTo(before.without(deletedGroup)));

  }

}
