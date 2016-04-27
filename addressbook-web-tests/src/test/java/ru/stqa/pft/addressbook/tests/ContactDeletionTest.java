package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Sergey on 21.04.2016.
 */
public class ContactDeletionTest extends TestBase{

  @Test
  public void testContactDeletion (){
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactsHelper().isThereAContact()){
      app.getNavigationHelper().gotoAddNewPage();
      app.getContactsHelper().createContact(new ContactData("Sergey", null, null, "test1") , true);
    }
    app.getContactsHelper().selectContact ();
    app.getContactsHelper().deleteSelectedContact();
    app.getContactsHelper().deleteContactConfirmation();
    app.getNavigationHelper().gotoHomePage();
  }






}
