package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Sergey on 20.04.2016.
 */
public class NavigationHelper2 extends HelperBase {

  public NavigationHelper2(FirefoxDriver wd) {
    super (wd);
  }

  public void gotoAddContactPage() {
      click(By.xpath("//form[@id='LoginForm']/input[3]"));
  }
}
