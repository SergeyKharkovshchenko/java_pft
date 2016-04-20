package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager2;

/**
 * Created by Sergey on 20.04.2016.
 */
public class TestBase2 {

  protected final ApplicationManager2 app2 = new ApplicationManager2();

  @BeforeMethod
  public void setUp() throws Exception {
    app2.init();
  }

  @AfterMethod
  public void tearDown() {
    app2.stop();
  }

}
