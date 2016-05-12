package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Sergey on 22.04.2016.
 */
public class ContactsModificationTests extends TestBase {

  @Test (enabled = false)
  public void testContactsModification (){
    app.goTo().gotoHomePage();
    List<ContactData> before = app.getContactsHelper().getContactsList();
    app.getContactsHelper().selectContact (before.size() - 1);
    app.getContactsHelper().initContactModification(before.size() - 1);
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"Sergey1", "Kharkovshchenko1", "email@email.ru1", null) ;
    app.getContactsHelper().fillContactsForm ( contact , false);
    app.getContactsHelper().submitContactModification();
    app.goTo().gotoHomePage();
    List<ContactData> after = app.getContactsHelper().getContactsList();
    Assert.assertEquals (after.size(), before.size());

    before.remove (before.size() - 1);
    before.add (contact);

    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());;
    before.sort(byId);
    after.sort(byId);
    //Assert.assertEquals(  new HashSet<Object>(before), new HashSet <Object> (after));
    Assert.assertEquals(  before, after);
  }
}
