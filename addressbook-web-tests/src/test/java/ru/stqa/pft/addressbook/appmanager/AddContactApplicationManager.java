package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Sergey on 20.04.2016.
 */
public class AddContactApplicationManager {
  FirefoxDriver wd;

  private AddContactSessionHelper addContactSessionHelper;
  private AddContactNavigationHelper addContactNavigationHelper;
  private AddContactHelper addContactHelper;


  public void init() {
    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/edit.php");
    addContactHelper = new AddContactHelper(wd);
    addContactNavigationHelper = new AddContactNavigationHelper(wd);
    addContactSessionHelper = new AddContactSessionHelper(wd);
    addContactSessionHelper.login("admin", "secret");
  }

  public void stop() {
    wd.quit();
  }

  public AddContactHelper getAddContactHelper() {
    return addContactHelper;
  }

  public AddContactNavigationHelper getAddContactNavigationHelper() {
    return addContactNavigationHelper;
  }
}
