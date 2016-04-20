package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.NewContactData;

/**
 * Created by Sergey on 20.04.2016.
 */
public class AddContactHelper extends HelperBase {

  public AddContactHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void returnToAddContactPage() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void addNewContact(NewContactData newContactData) {
    type(By.name("firstname"), newContactData.getName());
    type(By.name("lastname"), newContactData.getLastname());
    type(By.name("email"), newContactData.getEmail());
  }

}
