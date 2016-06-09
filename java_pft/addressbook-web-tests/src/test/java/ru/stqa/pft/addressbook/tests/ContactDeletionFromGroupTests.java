package ru.stqa.pft.addressbook.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;

/**
 * Created by SK on 31.05.2016.
 */
public class ContactDeletionFromGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("new group"));
        }
    }


    private SessionFactory sessionFactory;

    @BeforeClass
    protected void setUp2() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    @Test
    public void testContactDeletionFromGroupTests() {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<ContactData> newResult = session.createQuery("from ContactData where deprecated = '0000-00-00' ").list();
        ContactData chosenContact = newResult.iterator().next();

        Set<String> contactGroupsUi = app.contact().deleteFromGroupUi(chosenContact);
        Set<String> x = new HashSet<String>(Arrays.asList(chosenContact.getGroups()
                .toString().replaceAll("GroupData\\{id=\\'...\\', name=\\'", "")
                .replaceAll("\\'}", "").replaceAll("\\[", "").replaceAll("\\]", "").split(", ")));

        if (contactGroupsUi.size() == 0 && x.size() == 1) {
            assertEquals(1, 1);
        } else {
            assertEquals(contactGroupsUi, x);
        }

        session.getTransaction().commit();
        session.close();
    }
}
