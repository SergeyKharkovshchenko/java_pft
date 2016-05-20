package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
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
public class ContactDetailsTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.goTo().gotoAddNewPage();
            app.contact().create(new ContactData().withName("Sergey").withLastname("Kharkovshchenko").withEmail("test1"));
        }
    }

    @Test
    public void testContactDetails () {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        String contactInfoFromDetailsForm = app.contact().infoFromDetailsForm(contact);
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat ((contactInfoFromEditForm.getName() + " " +
        contactInfoFromEditForm.getLastname() + "\n" +
        contactInfoFromEditForm.getAddress() + "\n" +
        contactInfoFromEditForm.getHomePhone() + "\n" +
        contactInfoFromEditForm.getMobilePhone() + "\n" +
        contactInfoFromEditForm.getWorkPhone() + "\n" +
        contactInfoFromEditForm.getEmail1() + "\n" +
        contactInfoFromEditForm.getEmail2() + "\n" +
        contactInfoFromEditForm.getEmail3()).replaceAll("\n\n","\n"),
                equalTo(contactInfoFromDetailsForm));
    }

}
