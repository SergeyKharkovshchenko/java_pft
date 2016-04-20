package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContactData;

public class AddNewContactTests extends AddContactTestBase {

    @Test
    public void testAddNewContact() {
        addContactApp.getAddContactNavigationHelper().gotoAddContactPage();
        addContactApp.getAddContactHelper().initContactCreation();
        addContactApp.getAddContactHelper().addNewContact(new NewContactData("Sergey", "Kharkovshchenko", "kharkovchenko@gmail.com"));
        addContactApp.getAddContactHelper().returnToAddContactPage();
    }

}
