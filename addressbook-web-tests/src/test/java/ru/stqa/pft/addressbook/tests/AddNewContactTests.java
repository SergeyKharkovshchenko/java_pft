package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContactData;

public class AddNewContactTests extends TestBase2 {

    @Test
    public void testAddNewContact() {
        app2.getNavigationHelper2().gotoAddContactPage();
        app2.getGroupHelper2().initContactCreation();
        app2.getGroupHelper2().addNewContact(new NewContactData("Sergey", "Kharkovshchenko", "kharkovchenko@gmail.com"));
        app2.getGroupHelper2().returnToAddContactPage();
    }

}
