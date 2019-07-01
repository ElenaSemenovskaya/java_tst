package se.tst.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import se.tst.addressbook.model.ContactDate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

  public void editConact(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
  }

  private void editContactById(int id) {
    wd.findElement(By.cssSelector("a[href='edit.php?id="+ id +"']")).click();
  }


  public void updateContact() {
    click((By.xpath("//input[@name='update']")));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  private void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value = '" + id + "']")).click();
  }

  public void deleteContact() {
    click((By.xpath("//input[@value='Delete']")));
  }

  public void modify(ContactDate contact) {
    editContactById(contact.getId());
    fillContactForm(contact, false);
    updateContact();
    gotoHomePage();
  }


  public void assertDeleteContact() {
    wd.switchTo().alert().accept();
  }

  public void create(ContactDate contact, boolean b) {
    initContactCreation();
    fillContactForm(contact, b);
    submit();
    gotoHomePage();
  }

  public void delete(int index) {
    selectContact(index);
    deleteContact();
    assertDeleteContact();

  }

  public void delete(ContactDate deletedContact) {
    selectContactById(deletedContact.getId());
    deleteContact();
    assertDeleteContact();
  }


  public void gotoHomePage() {
    click(By.linkText("home"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getCountContact() {
    return wd.findElements(By.name("selected[]")).size();
  }

    public List<ContactDate> list() {
      List<ContactDate> contacts = new ArrayList<ContactDate>();
      List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
      for (WebElement element : elements) {
        String name = element.findElement(By.xpath(".//td[3]")).getText();
        String lastname = element.findElement(By.xpath(".//td[2]")).getText();
        String address = element.findElement(By.xpath(".//td[4]")).getText();
        int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
        contacts.add(new ContactDate().withId(id).withName(name).withLastname(lastname).withAddress(address));
      }

      return contacts;
    }
    public Set<ContactDate> all() {
    Set<ContactDate> contacts = new HashSet<ContactDate>();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
    for (WebElement element : elements) {
      String name = element.findElement(By.xpath(".//td[3]")).getText();
      String lastname = element.findElement(By.xpath(".//td[2]")).getText();
      String address = element.findElement(By.xpath(".//td[4]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new ContactDate().withId(id).withName(name).withLastname(lastname).withAddress(address));
    }

    return contacts;
  }



}
