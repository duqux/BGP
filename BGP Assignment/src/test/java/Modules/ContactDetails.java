package Modules;

import Stepdefinition.Hooks;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

public class ContactDetails extends Hooks {

    int ZERO = 0;
    int count = ZERO;
    JSONParser parser = new JSONParser();
    Object obj;
    Object obj1;
    Logger logger = LoggerFactory.getLogger(Commons.class);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    {
        try {
            obj = parser.parse(new FileReader("src/test/java/Data/contactDetailsFields.json"));
            obj1 = parser.parse(new FileReader("src/test/java/Data/pageText.json"));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    JSONObject jsonObject = (JSONObject) obj;
    String postalCode = (String) jsonObject.get("Postal Code");
    String blkHouseNo = (String) jsonObject.get("Block/House No.");
    String street = (String) jsonObject.get("Street");
    String accountPostalCode = (String) jsonObject.get("Account Postal Code");
    String accountBlkHouseNo = (String) jsonObject.get("Account Block/House No.");
    String accountStreet = (String) jsonObject.get("Account Street");
    String accountLevel = (String) jsonObject.get("Account Level");
    String accountUnit = (String) jsonObject.get("Account Unit");
    String name = (String) jsonObject.get("Name");
    String incorrectContactNo = (String) jsonObject.get("Incorrect Contact No.");

    String incorrectEmail1 = (String) jsonObject.get("Incorrect Email 1");
    String incorrectEmail2 = (String) jsonObject.get("Incorrect Email 2");
    String incorrectEmail3 = (String) jsonObject.get("Incorrect Email 3");
    String jobTitle = (String) jsonObject.get("Job Title");
    String contactNo = (String) jsonObject.get("Contact No.");
    String Email = (String) jsonObject.get("Email");

    JSONObject jsonObject1 = (JSONObject) obj1;
    String contactNoErrorMessage = (String) jsonObject1.get("Contact No. error message");
    String emailErrorMessage = (String) jsonObject1.get("Email error message");
    String scenario = getScenario();

    public void fieldCheck(){
        count = driver.findElements(By.xpath("//div[@class='form-horizontal']//label[contains(text(),'')]")).size();
        for (int i = 1; i <= count; i++) {
            String fieldValue = (String) jsonObject.get("Field "+ i);
            WebElement fieldTitle = driver.findElement(By.xpath("//div[@class='form-horizontal']//label[contains(text()," +
                "'"+fieldValue+"')]"));
            Assert.assertTrue(fieldTitle.isDisplayed());
        }
    }

    public void autoPopulateAdd() throws InterruptedException {
        WebElement postalCodeTextBox = driver.findElement(By.xpath("//input[@id='react-contact_info-correspondence_address-postal']"));
        postalCodeTextBox.sendKeys(postalCode);
        postalCodeTextBox.sendKeys(Keys.ENTER);
        Thread.sleep(1000);
    }

    public void checkAutoPopulateAdd() throws InterruptedException {
        scrollBy(100);
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='react-contact_info-correspondence_address-block']")).getAttribute(
                "value").equalsIgnoreCase(blkHouseNo));
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='react-contact_info-correspondence_address-street']")).getAttribute(
                "value").equalsIgnoreCase(street));
    }

    public void clickSameAdd(){
        WebElement sameAddCheckBox = driver.findElement(By.id("react-contact_info-correspondence_address-copied"));
        sameAddCheckBox.click();
    }

    public void checkSameAdd() throws InterruptedException {
        scrollBy(100);
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='react-contact_info-correspondence_address-postal']")).getAttribute(
                "value").equalsIgnoreCase(accountPostalCode));
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='react-contact_info-correspondence_address-block" +
                "']")).getAttribute(
                "value").equalsIgnoreCase(accountBlkHouseNo));
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='react-contact_info-correspondence_address-street" +
                "']")).getAttribute(
                "value").equalsIgnoreCase(accountStreet));
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='react-contact_info-correspondence_address-level" +
                "']")).getAttribute(
                "value").equalsIgnoreCase(accountLevel));
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='react-contact_info-correspondence_address-unit" +
                "']")).getAttribute(
                "value").equalsIgnoreCase(accountUnit));
    }

    public void fillInPersonDetails(){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h2[contains(text(),'Provide Your Contact Details')]")));
        WebElement mainPersonNameTextBox = driver.findElement(By.xpath("//input[@id='react-contact_info-name']"));
        WebElement mainPersonJobTitleTextBox = driver.findElement(By.xpath("//input[@id='react-contact_info-designation']"));
        WebElement mainPersonContactNoTextBox = driver.findElement(By.xpath("//input[@id='react-contact_info-phone']"));
        WebElement mainPersonEmailTextBox = driver.findElement(By.xpath("//input[@id='react-contact_info-primary_email']"));

        mainPersonNameTextBox.sendKeys(name);
        mainPersonJobTitleTextBox.sendKeys(jobTitle);
        mainPersonContactNoTextBox.sendKeys(contactNo);
        mainPersonEmailTextBox.sendKeys(Email);

    }

    public void fillInIncorrectPersonDetails(){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h2[contains(text(),'Provide Your Contact Details')]"))); WebElement mainPersonContactNoTextBox = driver.findElement(By.xpath("//input[@id='react-contact_info-phone']"));
        WebElement mainPersonEmailTextBox = driver.findElement(By.xpath("//input[@id='react-contact_info-primary_email']"));
        mainPersonContactNoTextBox.sendKeys(incorrectContactNo  + Keys.TAB);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//p[contains(text(),'"+contactNoErrorMessage+"')]")));
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'"+contactNoErrorMessage+"')]")).isDisplayed());

        mainPersonEmailTextBox.sendKeys(incorrectEmail1 + Keys.TAB);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//p[contains(text(),'"+emailErrorMessage+"')]")));
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'"+emailErrorMessage+"')]")).isDisplayed());
        mainPersonEmailTextBox.clear();
        mainPersonEmailTextBox.sendKeys(incorrectEmail2  + Keys.TAB);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//p[contains(text(),'"+emailErrorMessage+"')]")));
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'"+emailErrorMessage+"')]")).isDisplayed());
        mainPersonEmailTextBox.clear();
        mainPersonEmailTextBox.sendKeys(incorrectEmail3  + Keys.TAB);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//p[contains(text(),'"+emailErrorMessage+"')]")));
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'"+emailErrorMessage+"')]")).isDisplayed());
    }

    public void fillInIncorrectLOOA() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h2[contains(text(),'Provide Your Contact Details')]")));  scrollBy(200);
        WebElement loaEmail = driver.findElement(By.xpath("//input[@id='react-contact_info-offeree_email']"));
        loaEmail.sendKeys(incorrectEmail1 + Keys.TAB);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//p[contains(text(),'"+emailErrorMessage+"')]")));
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'"+emailErrorMessage+"')]")).isDisplayed());
        loaEmail.clear();
        loaEmail.sendKeys(incorrectEmail2  + Keys.TAB);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//p[contains(text(),'"+emailErrorMessage+"')]")));
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'"+emailErrorMessage+"')]")).isDisplayed());
        loaEmail.clear();
        loaEmail.sendKeys(incorrectEmail3  + Keys.TAB);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//p[contains(text(),'"+emailErrorMessage+"')]")));
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'"+emailErrorMessage+"')]")).isDisplayed());

    }

    public void checkPersonDetails(){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h2[contains(text(),'Provide Your Contact Details')]"))); if(scenario.equalsIgnoreCase("TC007_ContactDetails") || scenario.equalsIgnoreCase("TC008_ContactDetails") || scenario.equalsIgnoreCase("TC010_ContactDetails")) {
            driver.switchTo().alert().accept();
        }
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@id='react-contact_info-name']")));
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='react-contact_info-name']")).getAttribute(
                "value").equalsIgnoreCase(name));
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='react-contact_info-designation']")).getAttribute(
                "value").equalsIgnoreCase(jobTitle));
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='react-contact_info-phone']")).getAttribute(
                "value").equalsIgnoreCase(contactNo));
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='react-contact_info-primary_email']")).getAttribute(
                "value").equalsIgnoreCase(Email));
    }

    public void fillInLetterOfAddressee(){
        WebElement loaName = driver.findElement(By.xpath("//input[@id='react-contact_info-offeree_name']"));
        WebElement loaJobTitle = driver.findElement(By.xpath("//input[@id='react-contact_info-offeree_designation']"));
        WebElement loaEmail = driver.findElement(By.xpath("//input[@id='react-contact_info-offeree_email']"));

        loaName.sendKeys(name);
        loaJobTitle.sendKeys(jobTitle);
        loaEmail.sendKeys(Email);
    }

    public void clickSameMainContact(){
        WebElement sameMainContactCheckBox = driver.findElement(By.id("react-contact_info-copied"));
        sameMainContactCheckBox.click();
    }

    public void checkSameMainContact() throws InterruptedException {
        if(scenario.equalsIgnoreCase("TC009_ContactDetails")) {
            driver.switchTo().alert().accept();
        }
        scrollBy(100);
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='react-contact_info-offeree_name']")).getAttribute(
                "value").equalsIgnoreCase(name));
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='react-contact_info-offeree_designation']")).getAttribute(
                "value").equalsIgnoreCase(jobTitle));
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='react-contact_info-offeree_email']")).getAttribute(
                "value").equalsIgnoreCase(Email));
    }

    public void verifyErrorMessage() throws InterruptedException {
        scrollBy(200);
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id = 'react-contact_info-offeree_name' and @class='form-control bgp-textfield has-error']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id = 'react-contact_info-offeree_designation' and " +
                "@class='form-control bgp-textfield has-error']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id = 'react-contact_info-offeree_email' and " +
                "@class='form-control bgp-textfield has-error']")).isDisplayed());
   }

    public void checkMandatory() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h2[text()='Provide Your Contact Details']")));
        Assert.assertTrue(driver.findElement(By.xpath("//p[@Id='react-contact_info-name-alert']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@Id='react-contact_info-designation-alert']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@Id='react-contact_info-phone-alert']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@Id='react-contact_info-primary_email-alert']")).isDisplayed());
        scrollBy(100);
        Assert.assertTrue(driver.findElement(By.xpath("//p[@Id='react-contact_info-correspondence_address-postal-alert']")).isDisplayed());
        scrollBy(200);
        Assert.assertTrue(driver.findElement(By.xpath("//p[@Id='react-contact_info-offeree_name-alert']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@Id='react-contact_info-offeree_designation-alert']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@Id='react-contact_info-offeree_email-alert']")).isDisplayed());
    }

}
