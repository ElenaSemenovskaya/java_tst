package se.tst.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import se.tst.addressbook.model.GroupDate;

import java.util.Set;

public class GroupModifyTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions () {
    app.goTo().GroupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupDate().withName("tst4"));
    }
  }

  @Test
  public void testGroupModify () {
    Set<GroupDate> before = app.group().all();
    GroupDate modifedGroup = before.iterator().next();
    GroupDate group = new GroupDate()
            .withId(modifedGroup.getId()).withName("tst10"). withHeader("tst11"). withFooter("tst12");
    app.group().modify(group);
    Set<GroupDate> after = app.group().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifedGroup);
    before.add(group);
    Assert.assertEquals(before, after);

  }

  }
