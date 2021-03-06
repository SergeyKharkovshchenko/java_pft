package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.*;

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
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        attach(By.name("photo"), contactData.getPhoto());

        if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }


    public void submitAddContact() {
        click(By.xpath("//input[@type='submit']"));
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void deleteSelectedContact() {
        click(By.xpath("//div/div[4]/form[2]/div[2]/input"));
    }

    public void deleteContactConfirmation() {
        confirmDeletion();
    }

    public void initContactModification(int id) {
        wd.findElement(By.xpath("//a[@href='edit.php?id=" + id + "']")).click();

    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void create(ContactData contactData) {
        fillContactsForm(contactData, true);
        submitAddContact();
    }

    public void modify(ContactData contact) {
        selectContactById(contact.getId());
        initContactModification(contact.getId());
        fillContactsForm(contact, false);
        submitContactModification();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        deleteContactConfirmation();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[1]/input"));

    }

    public int getContactsCount() {
        return wd.findElements(By.name("selected[]")).size();
    }


    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastname = cells.get(1).getText();
            String name = cells.get(2).getText();
            String email = cells.get(4).getText();
            String allPhones = cells.get(5).getText();
            String allEmails = cells.get(4).getText();
            String[] phones = cells.get(5).getText().split("\n");
            contacts.add(new ContactData().withId(id).withName(name).withLastname(lastname)
                            .withHomePhone(phones[0]).withMobilePhone(phones[1]).withWorkPhone(phones[2])
//      .withAllPhones(allPhones).withAllEmails(allEmails)
            );
        }
        return contacts;
    }


    /*
  public Set<ContactData> all() {
    Set<ContactData> contacts = new HashSet<ContactData>();
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for (WebElement row : rows) {
      List <WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String[] phones = cells.get(5).getText().split("\n");
      contacts.add(new ContactData().withId(id).withName(firstname).withLastname(lastname)
              .withHomePhone(phones[0]).withMobilePhone(phones[1]).withWorkPhone(phones[2]));
    }
    return contacts;
  }
*/

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String name = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email1 = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withName(name).withLastname(lastname).withAddress(address)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
                .withEmail1(email1).withEmail2(email2).withEmail3(email3);
    }

    public String infoFromDetailsForm(ContactData contact) {
        initContactDetailsById(contact.getId());
        String[] details = wd.findElement(By.xpath("//div[@id='content']")).getText().split("\n\n\nMember");
        wd.navigate().back();
        return details[0].replaceAll("W: ", "").replaceAll("M: ", "").replaceAll("H: ", "")
                .replaceAll(" \\(www.*\\)", "").replaceAll("\n\n", "\n");
    }

    private void initContactModificationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();

        // wd.findElement (By.xpath (String.format ("//input[value='%s']/../../td[8]/a",id))).click();
        // wd.findElement (By.xpath (String.format ("//tr[.//input[@value='%s']]/td[8]/a",id))).click();
        // wd.findElement (By.cssSelector (String.format ("a[href='edit.php?id=%s']",id))).click();

    }

    private void initContactDetailsById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(6).findElement(By.tagName("a")).click();
    }

    public Set<String> addToNewGroupUi(ContactData contact) {

        click(By.linkText("home"));
        new Select(wd.findElement(By.name("group"))).selectByVisibleText("[all]");
        String[] contactGroupsInDropdownInArray = wd.findElement(By.xpath("//select[@name='to_group']")).getText().split("\n");
        Set<String> contactGroupsInDropdownInSet = new HashSet<String>(Arrays.asList(contactGroupsInDropdownInArray));

        if (takeContactGroupsFromEdit(contact).size() != 0) {
            Set<String> contactGroupsFromEditInSet = takeContactGroupsFromEdit(contact);
            contactGroupsInDropdownInSet.removeAll(contactGroupsFromEditInSet);
            if (contactGroupsInDropdownInSet.size() == 0) {
                click(By.linkText("groups"));
                click(By.name("new"));
                type(By.name("group_name"), "new group3");
                click(By.name("submit"));
                contactGroupsInDropdownInSet.add("new group3");
            }
            addContactToGroupByUi(contact, contactGroupsInDropdownInSet);
            return takeContactGroupsFromEdit(contact);
        } else {
            addContactToGroupByUi(contact, contactGroupsInDropdownInSet);
            Set<String> contactGroupsFromEditInSet = takeContactGroupsFromEdit(contact);
            return contactGroupsFromEditInSet;
        }
    }

    private void addContactToGroupByUi(ContactData contact, Set<String> contactGroupsInDropdownInSet) {
        click(By.linkText("home"));
        selectContactById(contact.getId());
        new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(contactGroupsInDropdownInSet.iterator().next());
        click(By.xpath("//input[@name='add']"));
    }

    private Set<String> takeContactGroupsFromEdit(ContactData contact) {
        click(By.linkText("home"));
        new Select(wd.findElement(By.name("group"))).selectByVisibleText("[all]");
        initContactDetailsById(contact.getId());
        if (wd.findElements(By.xpath("//i")).size() != 0) {
            String[] infoFromEdit = wd.findElement(By.xpath("//i")).getText().split("\n");
            String[] contactGroupsFromEditInArray = new String[infoFromEdit.length];
            for (int i = 0; infoFromEdit.length > (i * 2 + 2); i++) {
                contactGroupsFromEditInArray[i] = infoFromEdit[i * 2 + 2].replaceAll(",", "");
            }
            Set<String> contactGroupsFromEditInSet = new HashSet<String>(Arrays.asList(contactGroupsFromEditInArray));
            contactGroupsFromEditInSet.remove(null);
            click(By.linkText("home"));
            return contactGroupsFromEditInSet;
        } else {
            Set<String> contactGroupsFromEditInSet = new HashSet<String>(Arrays.asList());
            return contactGroupsFromEditInSet;
        }

    }

    public Set<String> deleteFromGroupUi(ContactData contact) {

        if (takeContactGroupsFromEdit(contact).size() == 0) {
            addToNewGroupUi(contact);
        }

        click(By.linkText("home"));
        String x = takeContactGroupsFromEdit(contact).iterator().next();
        new Select(wd.findElement(By.name("group"))).selectByVisibleText(x);
        selectContactById(contact.getId());
        click(By.xpath("//input[@name='remove']"));

        click(By.linkText("home"));
        new Select(wd.findElement(By.name("group"))).selectByVisibleText("[all]");
        return takeContactGroupsFromEdit(contact);
    }
}