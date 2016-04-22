package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by Sergey on 21.04.2016.
 */
public class ContactDeletionTest extends TestBase{

  @Test
  public void testContactDeletion (){
    app.getNavigationHelper().gotoHomePage();
    app.getContactsHelper().selectContact ();
    app.getContactsHelper().deleteSelectedContact();
    app.getContactsHelper().deleteContactConfirmation();
    app.getNavigationHelper().gotoHomePage();
  }






}
