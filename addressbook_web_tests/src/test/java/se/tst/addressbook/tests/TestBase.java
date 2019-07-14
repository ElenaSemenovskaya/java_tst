package se.tst.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import se.tst.addressbook.appmanager.ApplicationManager;
import se.tst.addressbook.model.ContactDate;
import se.tst.addressbook.model.Contacts;
import se.tst.addressbook.model.GroupDate;
import se.tst.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {

  Logger logger = LoggerFactory.getLogger(TestBase.class);

  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
    app.stop();
  }

  public ApplicationManager getApp() {
    return app;
  }

  @BeforeMethod (alwaysRun = true)
  public void logTestStart (Method m, Object [] p) {
    logger.info("Start test " + m.getName() + " with parameters " + Arrays.asList(p));
  }

  @AfterMethod (alwaysRun = true)
  public void logTestStop (Method m) {
    logger.info("Stop test " + m.getName());
  }

  public void veryfiGroupListInUI() {
    if (Boolean.getBoolean("verifyUI")) {
      Groups dbGroups = app.db().groups();
      Groups uigroups = app.group().all();
      assertThat(uigroups, equalTo(dbGroups.stream()
              .map((g) -> new GroupDate().withId(g.getId()).withName(g.getName()))
              .collect(Collectors.toSet())));
    }
  }

  public void veryfiContactListInUI() {
    if (Boolean.getBoolean("verifyUI")) {
      Contacts dbContacts = app.db().contacts();
      Groups uigroups = app.group().all();
      assertThat(uigroups, equalTo(dbContacts.stream()
              .map((c) -> new ContactDate().withId(c.getId()).withName(c.getName()))
              .collect(Collectors.toSet())));
    }
  }



}

