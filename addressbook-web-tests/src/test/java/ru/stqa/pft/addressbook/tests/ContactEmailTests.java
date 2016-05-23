package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by Sergey on 15.05.2016.
 */
public class ContactEmailTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.goTo().gotoAddNewPage();
            app.contact().create(new ContactData().withName("Sergey").withLastname("Kharkovshchenko").withEmail("test1"));
        }
    }

    @Test
    public void testContactEmails () {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList (contact.getEmail1(),contact.getEmail2(),contact.getEmail3())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactEmailTests::cleaned)
                .collect(Collectors.joining("\n"));
    }


    public static String cleaned (String email) {
        return email./*replaceAll ("\\s", "").*/replaceAll("[()]","");
    }

}
