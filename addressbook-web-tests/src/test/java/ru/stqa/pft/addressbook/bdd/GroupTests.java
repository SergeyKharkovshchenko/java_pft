package ru.stqa.pft.addressbook.bdd;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

/**
 * Created by SK on 16.06.2016.
 */
@CucumberOptions (features = "classpath:bdd", plugin = {"pretty", "html:build/cucumber-report"})
public class GroupTests extends AbstractTestNGCucumberTests {

}
