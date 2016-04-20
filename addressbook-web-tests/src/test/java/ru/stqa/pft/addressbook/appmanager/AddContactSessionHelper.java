package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Sergey on 20.04.2016.
 */
public class AddContactSessionHelper extends HelperBase {

  public AddContactSessionHelper(FirefoxDriver wd) {
    super (wd);
  }
  public void login(String user, String password) {
    type (By.name("user"), user);
    type (By.name("pass"), password);
  }

}
