package ru.stqa.pft.mantis.tests;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import java.io.IOException;
import java.sql.*;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by SK on 05.06.2016.
 */
public class UserPasswordChangeByAdministratorTests extends TestBase{

    @BeforeMethod
    public void startMailServer () {
        app.mail().start();
    }

    @Test
    public void testUserPasswordChangeByAdministratorTests() throws IOException, MessagingException {

        chooseUser();

        app.userPassChangeHelper().login();
        app.userPassChangeHelper().changeUserPassword(chooseUser());
        String email = String.format("user%s@localhost.localdomain",1);
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.userPassChangeHelper().finish(confirmationLink, "password");
        assertTrue (app.newSession().login("user1", "password"));
    }

    private Users chooseUser() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?serverTimezone=UTC&user=root&password=");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT id, username FROM mantis_user_table");
            Users users = new Users();
            while (rs.next()) {
                if (!(rs.getString("username").equals("administrator"))) {
                    users.add(new UserData().withId(rs.getInt("id")).withName(rs.getString("username")));
                }
            }
            rs.close();
            st.close();
            conn.close();
            return users;

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return null;
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }


    @AfterMethod(alwaysRun = true)
    public void stopMailServer () {
        app.mail().stop();
    }
}
