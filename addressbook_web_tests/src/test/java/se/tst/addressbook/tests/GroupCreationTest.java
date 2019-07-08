package se.tst.addressbook.tests;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import se.tst.addressbook.model.GroupDate;
import se.tst.addressbook.model.Groups;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupCreationTest extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroups (){
    List<Object[]> list= new ArrayList<Object[]>();
    list.add(new Object[] {new GroupDate().withName("name 1").withHeader("header 1").withFooter("footer 1")});
    list.add(new Object[] {new GroupDate().withName("name 2").withHeader("header 2").withFooter("footer 2")});
    list.add(new Object[] {new GroupDate().withName("name 3").withHeader("header 3").withFooter("footer 3")});
    return list.iterator();
  }


  @Test (dataProvider = "validGroups")
  public void testGroupCreation(GroupDate group) throws Exception {
    app.goTo().GroupPage();
    Groups before = app.group().all();
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size() + 1));
    Groups after = app.group().all();
    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));

  }

  @Test
  public void testBadGroupCreation() throws Exception {
    app.goTo().GroupPage();
    Groups before = app.group().all();
    GroupDate group = new GroupDate().withName("tst40' ");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.group().all();

    assertThat(after, equalTo(before));

  }

}
