package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Sergey on 21.04.2016.
 */
public class ContactsCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object []> validContactsFromXml() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream ();
      xstream.processAnnotations(ContactData.class);
      List<ContactData> contacts = (List <ContactData> ) xstream.fromXML(xml);
      return contacts.stream().map((g) -> new Object [] {g}).collect(Collectors.toList()).iterator();
    }

  }

  @Test (dataProvider = "validContactsFromXml")
  public void testAddNew(ContactData contact){
    Groups groups = app.db().groups();
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    app.goTo().gotoAddNewPage();
    app.contact().create(contact.inGroup(groups.iterator().next()));
    app.goTo().homePage();
    Contacts after = app.db().contacts();
    assertThat (after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

   verifyContactListInUI ();
  }

}
