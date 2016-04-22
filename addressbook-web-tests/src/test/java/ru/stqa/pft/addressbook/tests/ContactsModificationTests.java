package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Sergey on 22.04.2016.
 */
public class ContactsModificationTests extends TestBase {

  @Test
  public void testContactsModification (){
    app.getNavigationHelper().gotoHomePage();
    app.getContactsHelper().selectContact ();
    app.getContactsHelper().initContactModification();
    app.getContactsHelper().fillContactsForm ( new ContactData("Sergey1", "Kharkovshchenko1", "email@email.ru1"));
    app.getContactsHelper().submitContactModification();
  }
}
