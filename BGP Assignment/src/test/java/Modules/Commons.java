package Modules;

import Stepdefinition.Hooks;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

public class Commons extends Hooks {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    Actions action = new Actions(driver);
    JSONParser parser = new JSONParser();
    Object obj;
    Logger logger = LoggerFactory.getLogger(Commons.class);
    String scenario = getScenario();

    {
        try {
            obj = parser.parse(new FileReader("src/test/java/Data/login.json"));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    JSONObject jsonObject = (JSONObject) obj;

    public void navigate() {
        try {
            String url = (String) jsonObject.get("url");
            driver.navigate().to(url);
            WebElement signInTitle = driver.findElement(By.xpath("(//span[contains(text(),'Sign in')])[2]"));
            Assert.assertTrue(signInTitle.isDisplayed());
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    public void login() {
        try {
            String username = (String) jsonObject.get("username");
            String password = (String) jsonObject.get("password");
            String NRIC = (String) jsonObject.get("NRIC");
            String Name = (String) jsonObject.get("Name");
            String UEN = (String) jsonObject.get("UEN");
            String Role = (String) jsonObject.get("Role");
            WebElement usernameTextBox = driver.findElement(By.xpath("(//input[contains(@id,'signInFormUsername')])[2]"));
            WebElement passwordTextBox = driver.findElement(By.xpath("(//input[contains(@id,'signInFormPassword')])[2]"));
            WebElement signInButton = driver.findElement(By.xpath("(//input[contains(@name,'signInSubmitButton')])[2]"));
            action.moveToElement(usernameTextBox).perform();
            clickJS(usernameTextBox);
            usernameTextBox.sendKeys(username);
            passwordTextBox.sendKeys(password);
            signInButton.click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id,'login-button')]")));
            WebElement loginButton = driver.findElement(By.xpath("//div[contains(@id,'login-button')]"));
            loginButton.click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[contains(text(),'Manual')]")));
            //    WebElement manualTitle = driver.findElement(By.xpath("//h1[contains(text(),'Manual')]"));
            //    Assert.assertTrue(manualTitle.isDisplayed());

            WebElement entityIDTextBox = driver.findElement(By.xpath("//input[@id='entityId']"));
            WebElement userIDTextBox = driver.findElement(By.xpath("//input[@id='userId']"));
            WebElement userRoleTextBox = driver.findElement(By.xpath("//input[@id='userRole']"));
            WebElement fullNameTextBox = driver.findElement(By.xpath("//input[@id='userFullName']"));
            WebElement corpPassLoginButton = driver.findElement(By.xpath("//button[text()='Log In']"));

            entityIDTextBox.sendKeys(UEN);
            userIDTextBox.sendKeys(NRIC);
            userRoleTextBox.sendKeys(Role);
            fullNameTextBox.sendKeys(Name);
            corpPassLoginButton.click();

            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h2[contains(text(),'Grants')]")));
            WebElement myGrantsTitle = driver.findElement(By.xpath("//h2[contains(text(),'Grants')]"));
            Assert.assertTrue(myGrantsTitle.isDisplayed());
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    public void createNewGrant() throws InterruptedException {
        try {
            WebElement createNewGrantButton = driver.findElement(By.xpath("//div[@class='dashboard-action-card']//h4[contains(text(),'grant')]"));
            createNewGrantButton.click();

            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h3[contains(text(),'sector')]")));
            WebElement sectorTitle = driver.findElement(By.xpath("//h3[contains(text(),'sector')]"));
            Assert.assertTrue(sectorTitle.isDisplayed());

            WebElement sectorButton = driver.findElement(By.xpath("//div[contains(text(),'IT')]"));
            sectorButton.click();

            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h3[contains(text(),'need this grant')]")));
            WebElement grantTitle = driver.findElement(By.xpath("//h3[contains(text(),'need this grant')]"));
            Assert.assertTrue(grantTitle.isDisplayed());

            WebElement grantButton = driver.findElement(By.xpath("//div[contains(text(),'Bring')]"));
            grantButton.click();

            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h3[contains(text(),'plan')]")));
            WebElement grantApplyTitle = driver.findElement(By.xpath("//h3[contains(text(),'plan')]"));
            Assert.assertTrue(grantApplyTitle.isDisplayed());

            WebElement grantApplyButton = driver.findElement(By.xpath("//div[contains(text(),'Market Readiness')]"));
            grantApplyButton.click();

            WebElement applyButton = driver.findElement(By.xpath("//button[@id='go-to-grant']"));
            applyButton.click();
            Thread.sleep(100);
            scrollBy(1000);
            WebElement proceedButton = driver.findElement(By.xpath("//button[@id='keyPage-form-button']"));
            scrollTo(proceedButton);
            proceedButton.click();

            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h2[contains(text(),'Eligibility')]")));
            WebElement eligibilityTitle = driver.findElement(By.xpath("//h2[contains(text(),'Eligibility')]"));
            Assert.assertTrue(eligibilityTitle.isDisplayed());
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    public void clickOnSection(String section) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[contains(text(),'" + section +
                "') and @class='menu-text']")));
        WebElement sectionMenuTab = driver.findElement(By.xpath("//span[contains(text(),'" + section + "') and @class='menu-text']"));
        clickJS(sectionMenuTab);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h2[contains(text(),'Contact Details')]")));
        WebElement contactDetailsTitle = driver.findElement(By.xpath("//h2[contains(text(),'Contact Details')]"));
        Assert.assertTrue(contactDetailsTitle.isDisplayed());
    }

    public void logout() {
        WebElement logoutButton = driver.findElement(By.xpath("//div[@id='logout-button']"));
        logoutButton.click();
        if (!scenario.equalsIgnoreCase("TC001_ContactDetails") && !scenario.equalsIgnoreCase("TC007_ContactDetails") && !scenario.equalsIgnoreCase("TC008_ContactDetails") && !scenario.equalsIgnoreCase("TC009_ContactDetails") && !scenario.equalsIgnoreCase("TC010_ContactDetails") && !scenario.equalsIgnoreCase("DeleteDraft") && !scenario.equalsIgnoreCase("TC001_FormSubmission") && !scenario.equalsIgnoreCase("TC002_FormSubmission")&& !scenario.equalsIgnoreCase("TC003_FormSubmission")&& !scenario.equalsIgnoreCase("TC004_FormSubmission")) {
            driver.switchTo().alert().accept();
        }
    }

    public void next() throws InterruptedException {
        scrollBy(300);
        WebElement nextButton = driver.findElement(By.xpath("//button[@id='next-btn']"));
        clickJS(nextButton);
    }



    //just to remove my drafts
    public void deleteDraft() throws InterruptedException {
        WebElement draftTab = driver.findElement(By.xpath("(//a[contains(text(),'Drafts')])[1]"));
        WebElement myGrants = driver.findElement(By.xpath("//a[text()='My Grants']"));
        draftTab.click();
        int count = driver.findElements(By.xpath("//div[@class='title-div']")).size();
        for (int i=1; i<=count; i++){
            scrollBy(500);
            if(!"1".equalsIgnoreCase(String.valueOf(i))) {
                refresh();
                Thread.sleep(4000);
                scrollBy(500);
                WebElement draftTab1 = driver.findElement(By.xpath("(//a[contains(text(),'Drafts')])[1]"));
               // wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("(//a[contains(text(),'Drafts')])[1]")));
                clickJS(draftTab1);
            }
            try {
                Thread.sleep(2000);
             //   WebElement draftTitle = driver.findElement(By.xpath("(//div[text()='untitled'])[1]"));
                WebElement draftTitle = driver.findElement(By.xpath("(//div[text()='17 Carat Project'])[1]"));
                draftTitle.click();
            }
            catch(Exception e){
               // WebElement draftTitle1 = driver.findElement(By.xpath("(//div[text()='untitled'])[1]"));
                WebElement draftTitle1 = driver.findElement(By.xpath("(//div[text()='17 Carat Project'])[1]"));
                draftTitle1.click();
            }
                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h2[text()='Grant Actions']")));
                scrollBy(1000);
                try {
                    Thread.sleep(2000);
                    WebElement deleteButton = driver.findElement(By.xpath("//a[@id='keyPage-delete-button']"));
                    deleteButton.click();
                }
                catch(Exception e){
                    WebElement deleteButton1 = driver.findElement(By.xpath("//a[@id='keyPage-delete-button']"));
                    deleteButton1.click();
                }
                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[@class='bgp-btn bgp-btn-modal-delete']")));
               try {
                   Thread.sleep(2000);
                   WebElement confirmDeleteButton = driver.findElement(By.xpath("//button[@class='bgp-btn bgp-btn-modal-delete']"));
                   confirmDeleteButton.click();
               }
               catch(Exception ex){
                   WebElement confirmDeleteButton1 = driver.findElement(By.xpath("//button[@class='bgp-btn " +
                           "bgp-btn-modal-delete']"));
                   confirmDeleteButton1.click();
               }
                Thread.sleep(4000);
        }
    }

}
