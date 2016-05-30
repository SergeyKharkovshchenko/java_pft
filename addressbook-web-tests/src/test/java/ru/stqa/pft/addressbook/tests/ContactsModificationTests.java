package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by Sergey on 22.04.2016.
 */
public class ContactsModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.goTo().gotoAddNewPage();
      app.contact().create(new ContactData().withName("Sergey").withLastname("Kharkovshchenko").withEmail("test 3")
              .withPhoto(new File ((String) "src\\test\\resources\\stru.png"))
              .withGroup("test 0")
              .withHomePhone("111").withMobilePhone("111").withWorkPhone("111"));
    }
  }

  @Test
  public void testContactsModification() {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withName("Sergey1").withLastname("Kharkovshchenko1").withPhoto(new File ((String) "src\\test\\resources\\stru.png"))
//
            .withHomePhone("111").withMobilePhone("111").withWorkPhone("111");
    app.goTo().homePage();
    app.contact().modify(contact);
    Contacts after = app.db().contacts();
    assertEquals(after.size(), before.size());

    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    app.goTo().homePage();
    verifyContactListInUI ();
  }
}
