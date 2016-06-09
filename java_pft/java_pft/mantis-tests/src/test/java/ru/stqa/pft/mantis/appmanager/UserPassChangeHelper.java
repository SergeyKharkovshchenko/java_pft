package ru.stqa.pft.mantis.appmanager;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.Users;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SK on 06.06.2016.
 */
public class UserPassChangeHelper extends  HelperBase{

    private CloseableHttpClient httpclient;

    public UserPassChangeHelper(ApplicationManager app) {
        super (app);
    }

    public void login() {
        wd.get (app.getProperty("web.baseUrl") + "/login_page.php");
        type (By.name("username"), "administrator");
        type (By.name("password"), "root");
        click (By.cssSelector("input[value='Login']"));
    }

    public void changeUserPassword(Users user) {
        wd.get (app.getProperty("web.baseUrl") + "/manage_user_edit_page.php?user_id=" + user.iterator().next().getId());
        click (By.cssSelector("input[value='Reset Password']"));
    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click (By.cssSelector("input[value='Update User']"));
    }

    private String getTextFrom(CloseableHttpResponse response)throws IOException {
        try {
            return EntityUtils.toString(response.getEntity());
        } finally {
            response.close();
        }
    }

}
