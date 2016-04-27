package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Sergey on 21.04.2016.
 */
public class ContactsHelper extends HelperBase {


  public ContactsHelper(WebDriver wd) {
    super(wd);
  }


  public void fillContactsForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getName());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("email"), contactData.getEmail());

    if (creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else{
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
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

  public void createContact(ContactData contactData, boolean b) {
  fillContactsForm ( contactData , true);
  submitAddContact();
  }

  public boolean isThereAContact() {
    return isElementPresent( By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[1]/input"));

  }
}
