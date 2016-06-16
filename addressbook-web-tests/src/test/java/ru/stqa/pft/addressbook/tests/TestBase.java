package ru.stqa.pft.addressbook.tests;

import com.sun.org.apache.xerces.internal.impl.dv.xs.BooleanDV;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.SystemClock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Sergey on 20.04.2016.
 */

@Listeners (MyTestListener.class)

public class TestBase {

  Logger logger = LoggerFactory.getLogger(TestBase.class);

  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite
  public void setUp(ITestContext context) throws Exception {
    app.init();
    context.setAttribute("app", app);
  }

  @AfterSuite (alwaysRun = true)
  public void tearDown() {
    app.stop();
  }

  @BeforeMethod
  public void LogTestStart (Method m, Object [] p) {
    logger.info("Start test " + m.getName() + "with parameter" + Arrays.asList(p));
  }

  @AfterMethod (alwaysRun = true)
  public void LogTestStop (Method m) {
    logger.info("Stop test " + m.getName());
  }


  public void verifyContactListInUI() {
    if (Boolean.getBoolean ("verifyUI")) {
      Contacts dbContacts = app.db().contacts();
      Contacts uiContacts = app.contact().all();

      assertThat(uiContacts, equalTo(dbContacts.stream()
              .map((g) -> new ContactData().withId(g.getId()).withName(g.getName()).withLastname(g.getLastname())
              .withWorkPhone(g.getWorkPhone().replaceAll("\\s", "").replaceAll("[-()]",""))
              .withHomePhone(g.getHomePhone().replaceAll("\\s", "").replaceAll("[-()]",""))
              .withMobilePhone(g.getMobilePhone().replaceAll("\\s", "").replaceAll("[-()]","")))
              .collect(Collectors.toSet())));
    }
  }


  public void verifyGroupListInUI() {
    if (Boolean.getBoolean ("verifyUI")) {
    Groups dbGroups = app.db().groups();
    Groups uiGroups = app.group().all();
    assertThat(uiGroups, equalTo(dbGroups.stream()
            .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
            .collect(Collectors.toSet())));
  }
  }
}
