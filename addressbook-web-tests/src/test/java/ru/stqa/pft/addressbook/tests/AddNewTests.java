package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

/**
 * Created by Sergey on 21.04.2016.
 */
public class AddNewTests extends TestBase {

  @Test
  public void testAddNew(){
    app.getNavigationHelper().gotoAddNewPage();
    app.getContactsHelper().fillContactsForm ( new ContactData("Sergey", "Kharkovshchenko", "email@email.ru"));
    app.getContactsHelper().submitAddContact();
    app.getNavigationHelper().gotoHomePage();

  };

}
