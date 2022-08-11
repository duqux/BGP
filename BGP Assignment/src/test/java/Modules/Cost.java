package Modules;

import Stepdefinition.Hooks;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
import java.io.*;
import java.nio.Buffer;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

public class Cost extends Hooks {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    Actions action = new Actions(driver);
    JSONParser parser = new JSONParser();
    Object obj;
    Logger logger = LoggerFactory.getLogger(Cost.class);
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

    public void fillInCost() throws InterruptedException, AWTException {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h2[contains(text(),'Provide Details of Costs')]")));
        WebElement thirdPartyVendorAccordion = driver.findElement(By.xpath("//div[@id='react-project_cost-vendors-accordion-header']"));

        thirdPartyVendorAccordion.click();
        WebElement addNewItemButton = driver.findElement(By.xpath("//button[@id='react-project_cost-vendors-add-item']"));
        addNewItemButton.click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@id='react-project_cost-vendors-0-local_vendor-true']")));
        WebElement vendorRadioButton = driver.findElement(By.xpath("//input[@id='react-project_cost-vendors-0-local_vendor-false']"));
        vendorRadioButton.click();
        Thread.sleep(5000);
        WebElement vendorNameTextBox = driver.findElement(By.xpath("//input[@id='react-project_cost-vendors-0-vendor_name']"));
        vendorNameTextBox.sendKeys(vendorName);
        WebElement selectFileButton = driver.findElement(By.xpath("//button[@id='react-project_cost-vendors-0" +
                "-attachments-btn']"));
        selectFileButton.click();
        Thread.sleep(5000);
        Robot rb = new Robot();
        StringSelection str = new StringSelection("C:\\Users\\ECQ985\\IdeaProjects\\BGP Assignment\\src\\main\\resources\\comprehensions.PNG");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);

        WebElement billingCurrencyTextBox = driver.findElement(By.xpath("//input[@id='react-project_cost-vendors-0-amount_in_billing_currency']"));
        billingCurrencyTextBox.sendKeys(billingCurrency);
    }
}
