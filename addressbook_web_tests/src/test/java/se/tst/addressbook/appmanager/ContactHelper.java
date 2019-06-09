package se.tst.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import se.tst.addressbook.model.ContactDate;

import static org.testng.Assert.assertTrue;

public class ContactHelper extends HelperBase{

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  private boolean acceptNextAlert = true;

  public void submit() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillContactForm(ContactDate contact, boolean creation) {
    type(By.name("firstname"), contact.getName());
    type(By.name("lastname"), contact.getLastname());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contact.getGroup());
    } else
      Assert.assertFalse(isElementPresent(By.name("new_group")));

    type(By.name("address"), contact.getLastname());
    type(By.name("address"), contact.getAddress());
    type(By.name("home"), contact.getTlfn());
    type(By.name("email"),contact.getMail());

  }

  public void initContactCreation() {
    click(By.name("firstname"));
  }

  public void editConact() {
    click((By.xpath("//img[@alt='Edit']")));
  }

  public void updateContact() {
    click((By.xpath("//input[@name='update']")));
  }

  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void deleteContact() {
    click((By.xpath("//input[@value='Delete']")));
  }

  public void assertDeleteContact() {
    wd.switchTo().alert().accept();
  }
}
