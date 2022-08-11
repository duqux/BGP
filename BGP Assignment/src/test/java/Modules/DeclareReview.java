package Modules;

import Stepdefinition.Hooks;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class DeclareReview extends Hooks {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    Actions action = new Actions(driver);
    JSONParser parser = new JSONParser();
    Object obj;
    Logger logger = LoggerFactory.getLogger(DeclareReview.class);
    String scenario = getScenario();

    {
        try {
            obj = parser.parse(new FileReader("src/test/java/Data/cost.json"));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    JSONObject jsonObject = (JSONObject) obj;

    String vendorName =  (String) jsonObject.get("Vendor Name");
    String billingCurrency =  (String) jsonObject.get("Billing Currency");

    public void fillInDeclareReview() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h2[contains(text(),'Declare & Acknowledge Terms')]")));

        try {
            List<WebElement> radioButtons = driver.findElements(By.xpath("//input[contains(@id,'react-declaration' and value='false')]"));
            for (int i = 0; i < 8; i++) {
                scrollBy(150);
                radioButtons.get(i).click();
            }

            List<WebElement> cvdRadioButtons = driver.findElements(By.xpath("//input[contains(@id," +
                    "'react-declaration-covid') and @value='true']"));
            int count = driver.findElements(By.xpath("//input[@id='react-declaration-criminal_liability_check-false']")).size();
            for (int i =0; i <= count; i++) {
                scrollBy(100);
                cvdRadioButtons.get(i).click();
            }

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    public void consentCheck() throws InterruptedException {
        scrollBy(200);
        WebElement consentCheckBox = driver.findElement(By.xpath("//input[@id='react-declaration-consent_acknowledgement_check']"));
        consentCheckBox.click();
    }

    public void clickReviewButton() throws InterruptedException {
        scrollBy(200);
        WebElement reviewButton = driver.findElement(By.xpath("//button[@id='review-btn']"));
        reviewButton.click();
    }

    public void checkReviewPage(){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h3[text()='Review Your " +
                "Application']")));
        Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Review Your Application']")).isDisplayed());
    }

    public void submitForReview() throws InterruptedException {
        scrollToTheEnd();
        WebElement declarationCheckBox = driver.findElement(By.xpath("//input[@id='react-declaration-info_truthfulness_check']"));
        declarationCheckBox.click();
        WebElement submitButton = driver.findElement(By.xpath("//button[@id='submit-btn']"));
        submitButton.click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h3[text()='Your application has been submitted.']")));
        Assert.assertTrue(driver.findElement(By.xpath("//td[contains(text(),'Ref ID')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//td[contains(text(),'Agency Details')]")).isDisplayed());
    }

    public void checkMandatory() throws InterruptedException {
        scrollToTheEnd();
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(@id,'react-declaration-consent_acknowledgement_check-alert')]")).isDisplayed());
    }
}