package se.tst.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import se.tst.addressbook.model.GroupDate;
import se.tst.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupModifyTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions () {
    if (app.db().groups().size() == 0) {
      app.goTo().GroupPage();
      app.group().create(new GroupDate().withName("tst4"));
    }
  }

  @Test
  public void testGroupModify () {
    Groups before = app.db().groups();
    GroupDate modifedGroup = before.iterator().next();
    GroupDate group = new GroupDate()
            .withId(modifedGroup.getId()).withName("tst10"). withHeader("tst11"). withFooter("tst12");
    app.goTo().GroupPage();
    app.group().modify(group);
    assertEquals(app.group().count(), before.size());
    Groups after = app.db().groups();
    assertThat(after, CoreMatchers.equalTo(before.without(modifedGroup).withAdded(group)));
    veryfiGroupListInUI();

  }

}
