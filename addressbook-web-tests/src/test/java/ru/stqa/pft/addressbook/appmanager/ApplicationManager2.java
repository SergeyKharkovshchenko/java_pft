package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Sergey on 20.04.2016.
 */
public class ApplicationManager2 {
  FirefoxDriver wd;

  private SessionHelper2 sessionHelper2;
  private NavigationHelper2 navigationHelper2;
  private AddContactHelper groupHelper2;


  public void init() {
    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/edit.php");
    groupHelper2 = new AddContactHelper(wd);
    navigationHelper2 = new NavigationHelper2(wd);
    sessionHelper2 = new SessionHelper2(wd);
    sessionHelper2.login("admin", "secret");
  }

  public void stop() {
    wd.quit();
  }

  public AddContactHelper getGroupHelper2() {
    return groupHelper2;
  }

  public NavigationHelper2 getNavigationHelper2() {
    return navigationHelper2;
  }
}
