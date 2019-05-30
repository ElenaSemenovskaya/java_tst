package se.tst.addressbook.tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import se.tst.addressbook.model.ContactDate;

public class AddNewContact {
  private WebDriver wd;


  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    wd = new ChromeDriver();
    login(new Login("admin", "secret"));

  }

  private void login(Login login) {
    wd.get("http://localhost/addressbook/");
    wd.findElement(By.name("user")).click();
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys(login.getUsername());
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys(login.getPassword());
    wd.findElement(By.xpath("//input[@value='Login']")).click();
  }

  @Test
  public void testAddNewContact() throws Exception {
    gotoContactList();
    initContactCreation();
    fillContactForm(new ContactDate("Name1", "LastName1", "Address", "tlf1", "mail@mail"));
    submit();
    logout();
  }

  private void logout() {
    wd.findElement(By.linkText("Logout")).click();
  }

  private void submit() {
    wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
  }

  private void fillContactForm(ContactDate contact) {
    wd.findElement(By.name("firstname")).sendKeys(contact.getName());
    wd.findElement(By.name("lastname")).clear();
    wd.findElement(By.name("lastname")).sendKeys(contact.getLastname());
    wd.findElement(By.name("address")).clear();
    wd.findElement(By.name("address")).sendKeys(contact.getAddress());
    wd.findElement(By.name("home")).clear();
    wd.findElement(By.name("home")).sendKeys(contact.getTlfn());
    wd.findElement(By.name("email")).clear();
    wd.findElement(By.name("email")).sendKeys(contact.getMail());
  }

  private void initContactCreation() {
    wd.findElement(By.name("firstname")).click();
    wd.findElement(By.name("firstname")).clear();
  }

  private void gotoContactList() {
    wd.findElement(By.linkText("add new")).click();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    wd.quit();

  }

  private boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }


}
