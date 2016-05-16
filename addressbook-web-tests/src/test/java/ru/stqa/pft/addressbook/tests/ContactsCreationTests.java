package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Sergey on 21.04.2016.
 */
public class ContactsCreationTests extends TestBase {

  @Test
  public void testAddNew(){
    app.goTo().homePage();
    Contacts before = app.contact().all();
    app.goTo().gotoAddNewPage();
    ContactData contact = new ContactData().withName("Sergey").withLastname("Kharkovshchenko1")
   .withGroup("test1").withEmail("test1")
     .withMobilePhone("111").withHomePhone("222").withWorkPhone("333")
;
    app.contact().create(contact);
    app.goTo().homePage();
    Contacts after = app.contact().all();
    assertThat (after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  };


}
