package se.tst.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {


  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void gotoGroupPage() {
    click(By.linkText("groups"));
  }

  public void gotoContactList() {
    click(By.linkText("add new"));
  }

  public void gotoScrollContact() {
    click(By.linkText("home"));
  }
}
