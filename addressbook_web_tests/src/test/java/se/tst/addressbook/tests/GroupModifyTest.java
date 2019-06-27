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
    Groups before = app.group().all();
    GroupDate modifedGroup = before.iterator().next();
    GroupDate group = new GroupDate()
            .withId(modifedGroup.getId()).withName("tst10"). withHeader("tst11"). withFooter("tst12");
    app.group().modify(group);
    Groups after = app.group().all();
    assertEquals(after.size(), before.size());
    assertThat(after, CoreMatchers.equalTo(before.without(modifedGroup).withAdded(group)));
    
    )

  }

  }
