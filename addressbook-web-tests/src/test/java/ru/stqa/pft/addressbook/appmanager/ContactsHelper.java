package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Sergey on 21.04.2016.
 */
public class ContactsHelper extends HelperBase {


  public ContactsHelper(FirefoxDriver wd) {
    super(wd);
  }


  public void fillContactsForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getName());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("email"), contactData.getEmail());
  }

  public void submitAddContact() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void selectContact() {
  click(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[1]/input"));
  }

 public void deleteSelectedContact() { click(By.xpath("//div/div[4]/form[2]/div[2]/input"));
 }

  public void deleteContactConfirmation() {  confirmDeletion ();     }

  public void initContactModification() { click(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[8]/a/img"));
 }

  public void submitContactModification() { click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }
}
