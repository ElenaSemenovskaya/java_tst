package se.tst.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import se.tst.addressbook.model.ContactDate;
import se.tst.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
    attach(By.name("photo"), contact.getPhoto());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contact.getGroup());
    } else
      Assert.assertFalse(isElementPresent(By.name("new_group")));

    type(By.name("address"), contact.getAddress());
    type(By.name("home"), contact.getHomePhone());
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
    contactCache = null;
    gotoHomePage();
  }


  public void assertDeleteContact() {
    wd.switchTo().alert().accept();
  }

  public void create(ContactDate contact, boolean b) {
    initContactCreation();
    fillContactForm(contact, b);
    submit();
    contactCache = null;
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
    contactCache = null;
    assertDeleteContact();
  }


  public void gotoHomePage() {
    click(By.linkText("home"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
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

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
    for (WebElement element : elements) {
      String name = element.findElement(By.xpath(".//td[3]")).getText();
      String lastname = element.findElement(By.xpath(".//td[2]")).getText();
      String address = element.findElement(By.xpath(".//td[4]")).getText();
      String allPhones = element.findElement(By.xpath(".//td[6]")).getText();
      String allmail = element.findElement(By.xpath(".//td[5]")).findElements(By.tagName("a")).stream().map(WebElement::getText).collect(Collectors.joining());
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contactCache.add(new ContactDate().withId(id).withName(name).withLastname(lastname).withAddress(address)
              .withAllPhones(allPhones)
              .withAllMail(allmail));
    }
    return new Contacts(contactCache);
  }


  public ContactDate infoFormEditForm(ContactDate contact) {
    initContactModificationById(contact.getId());
    String name = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String tlfnhome = wd.findElement(By.name("home")).getAttribute("value");
    String tlfnmobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String tlfnwork = wd.findElement(By.name("work")).getAttribute("value");
    String mail = wd.findElement(By.name("email")).getAttribute("value");
    String mail2 = wd.findElement(By.name("email2")).getAttribute("value");
    String mail3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactDate()
            .withId(contact.getId()).withName(name).withLastname(lastname).withAddress(address)
            .withHomePhone(tlfnhome).withMobilePhone(tlfnmobile).withWorkPhone(tlfnwork)
            .withMail(mail).withMail2(mail2).withMail3(mail3);

  }

  private void initContactModificationById(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }
}
