package ru.stqa.pft.rest;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;

/**
 * Created by SK on 12.06.2016.
 */
public class TestBase {

    public void skipIfNotFixed(int issueId) {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    boolean isIssueOpen(int issueId) {
        String json = RestAssured.get("http://demo.bugify.com/api/issues.json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        if (issues.getAsJsonArray().get(issueId).toString().contains("state_name\":\"Closed\"") ||
                issues.getAsJsonArray().get(issueId).toString().contains("state_name\":\"Resolved\"")    ) {
            return true;
        } else {
            return false;
        }
    }
}
