package se.tst.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import se.tst.addressbook.model.GroupDate;
import se.tst.addressbook.model.Groups;

import java.util.List;

public class GroupHelper extends HelperBase{

  public GroupHelper(WebDriver wd) {
    super(wd);

  }

  public void returnToGroupPage() {
    click(By.linkText("group page"));
  }

  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  public void fillGroupForm(GroupDate groupDate) {
    type(By.name("group_name"), groupDate.getName());
    type(By.name("group_header"), groupDate.getHeader());
    type(By.name("group_footer"), groupDate.getFooter());

  }

  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void deleteSelectedGroups() {
    click(By.name("delete"));
  }

  public void selectGroupById(int id) {
    wd.findElement(By.cssSelector("input[value = '" + id + "']")).click();
  }

  public void initGroupModify() {
    click(By.name("edit"));
  }

  public void submitGroupModify() {
    click(By.name("update"));
  }

  public void create(GroupDate group) {
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    groupCache = null;
    returnToGroupPage();
  }

  public void modify(GroupDate group) {
    selectGroupById(group.getId());
    initGroupModify();
    fillGroupForm(group);
    submitGroupModify();
    groupCache = null;
    returnToGroupPage();
  }


  public void delete(GroupDate group) {
    selectGroupById(group.getId());
    deleteSelectedGroups();
    groupCache = null;
    returnToGroupPage();

  }

  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Groups groupCache = null;

  public Groups all() {
    if (groupCache != null) {
      return new Groups(groupCache);
    }

    groupCache = new Groups();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element: elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      groupCache.add(new GroupDate().withId(id).withName(name));
    }
    return new Groups(groupCache);
  }


}
