package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Sergey on 21.04.2016.
 */
public class ContactsCreationTests extends TestBase {

  @Test (enabled = false)
  public void testAddNew(){
    app.goTo().gotoHomePage();
    List<ContactData> before = app.getContactsHelper().getContactsList();
    app.goTo().gotoAddNewPage();
    ContactData contact = new ContactData("Sergey", "Kharkovshchenko1", null, "test1");
    app.getContactsHelper().createContact(contact , true);
    app.goTo().gotoHomePage();
    List<ContactData> after = app.getContactsHelper().getContactsList();
    Assert.assertEquals (after.size(), before.size() + 1);

    contact.setId (after.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId());
    before.add (contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());;
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
  };

}
