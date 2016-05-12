package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by Sergey on 21.04.2016.
 */
public class ContactDeletionTest extends TestBase {

  @Test (enabled = false)
  public void testContactDeletion() {
    app.goTo().gotoHomePage();
    if (!app.getContactsHelper().isThereAContact()) {
      app.goTo().gotoAddNewPage();
      app.getContactsHelper().createContact(new ContactData("Sergey", null, null, "test1"), true);
    }
    List<ContactData> before = app.getContactsHelper().getContactsList();
    app.getContactsHelper().selectContact(before.size() - 1);
    app.getContactsHelper().deleteSelectedContact();
    app.getContactsHelper().deleteContactConfirmation();
    app.goTo().gotoHomePage();
    List<ContactData> after = app.getContactsHelper().getContactsList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }
}
