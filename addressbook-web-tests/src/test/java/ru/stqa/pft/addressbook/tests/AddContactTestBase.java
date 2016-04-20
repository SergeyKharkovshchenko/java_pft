package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.appmanager.AddContactApplicationManager;

/**
 * Created by Sergey on 20.04.2016.
 */
public class AddContactTestBase {

  protected final AddContactApplicationManager addContactApp = new AddContactApplicationManager();

  @BeforeMethod
  public void setUp() throws Exception {
    addContactApp.init();
  }

  @AfterMethod
  public void tearDown() {
    addContactApp.stop();
  }

}
