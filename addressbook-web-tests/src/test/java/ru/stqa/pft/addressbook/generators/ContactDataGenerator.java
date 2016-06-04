package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SK on 20.05.2016.
 */
public class ContactDataGenerator {
    @Parameter (names ="-c", description = "Contacts count")
    public int count;

    @Parameter(names ="-f", description = "Target file")
    public String file;

    @Parameter(names ="-d", description = "Data format")
    public String format;


    public static void main (String [] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("xml")) {
            saveAsXml(contacts, new File(file));
        }  else {
            System.out.println("Unrecognized format" + format);
        }
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream ();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }

    private List<ContactData> generateContacts(int count) {
        List <ContactData> contacts = new ArrayList<ContactData>();
        for (int i=0; i<count;i++) {
            File photo = new File("src/test/resources/stru.png");
            contacts.add(new ContactData().withName(String.format("test1 %s", i)).withLastname(String.format("test2 %s", i))
//                    .withGroup(String.format("test1"))
                    .withMobilePhone(String.format("111 %s", i)).withHomePhone(String.format("222 %s", i)).withWorkPhone(String.format("333 %s", i))
                    .withEmail1(String.format("test1 %s", i)).withEmail2(String.format("test2 %s", i)).withEmail3(String.format("test3 %s", i))
            .withPhoto(photo));
        }
        return contacts;
    }
}
