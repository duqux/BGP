package Modules;

import Stepdefinition.Hooks;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class EligibilitySection extends Hooks {
    int ZERO = 0;
    int count = ZERO;
    JSONParser parser = new JSONParser();
    Object obj;
    Object obj1;
    private static final Logger logger = LoggerFactory.getLogger(EligibilitySection.class);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    {
        try {
            obj = parser.parse(new FileReader("src/test/java/Data/questions.json"));
            obj1 = parser.parse(new FileReader("src/test/java/Data/pageText.json"));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    JSONObject jsonObject = (JSONObject) obj;
    JSONObject jsonObject1 = (JSONObject) obj1;

    public void checkQuestions() {
        try {
            count = driver.findElements(By.xpath("//label[@class='control-label bgp-label']")).size();
            for (int i = 1; i <= count; i++) {
                String questions = driver.findElement(By.xpath("(//label[@class='control-label bgp-label'])[" + i + "]")).getText();
                String questionData = (String) jsonObject.get("question" + i);
                Assert.assertTrue(questions.contains(questionData));
                logger.debug("Question " + i + " is correct.");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    public void checkRadioButtons() {
        try {
            WebElement radioButton = driver.findElement(By.xpath("//input[contains(@name,'react-eligibility')]"));
            Assert.assertTrue(radioButton.isEnabled());
            logger.debug("The questions are using radio buttons");
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    public void radiobuttonAllClick(String option){
        try {
            if ("Yes".equalsIgnoreCase(option)) {
                option = "true";
            } else {
                option = "false";
            }
            List<WebElement> radioButtons = driver.findElements(By.xpath("//input[contains(@name,'react-eligibility-') and @value='" + option + "']"));
            count = driver.findElements(By.xpath("//input[contains(@name,'react-eligibility-') and @value='" + option + "']")).size();
            for (int i = 0; i < count; i++) {
                scrollBy(100);
                radioButtons.get(i).click();
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }
    public void radiobuttonClick(String option, String path) {
        try {
            if ("Yes".equalsIgnoreCase(option)) {
                option = "true";
            } else {
                option = "false";
            }

            WebElement radioButton = driver.findElement(By.xpath("//input[contains(@name,'react-eligibility-" + path +
                    "') and @value='" + option + "']"));
            radioButton.click();
            logger.debug("Radio button is clicked.");
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }


    public void checkErrorMessage() {
        try {
            String errorMessageText = (String) jsonObject1.get("errorMessage");
            WebElement errorMessage = driver.findElement(By.xpath("//span[contains(text(),'" + errorMessageText + "')]"));
            Assert.assertTrue(errorMessage.isDisplayed());
            logger.debug("Error message is accurate.");
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    public void saveDraft(){
        try{
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[@id='save-btn']")));
            WebElement saveDraftButton = driver.findElement(By.xpath("//button[@id='save-btn']"));
            saveDraftButton.click();
            logger.debug("Draft Saved.");
            refresh();
        }
        catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    public void checkDraft(String option){
        try {
            if ("Yes".equalsIgnoreCase(option)) {
                option = "true";
            } else {
                option = "false";
            }
            driver.switchTo().alert().accept();
            List<WebElement> radioButtons = driver.findElements(By.xpath("//input[contains(@name,'react-eligibility-') and @value='" + option + "']"));
            count = driver.findElements(By.xpath("//input[contains(@name,'react-eligibility-') and @value='" + option + "']")).size();
            for (int i = 0; i < count; i++) {
                Assert.assertTrue(radioButtons.get(i).isSelected());
                logger.debug("Draft Saved for question " + i);
            }
        }
        catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    public void checkMandatory(){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h2[text()='Check Your Eligibility']")));
        count = driver.findElements(By.xpath("//input[contains(@id,'react-eligibility-')]")).size();
        for(int i=1;i<count;i++){
            Assert.assertTrue(driver.findElement(By.xpath("(//input[contains(@id,'react-eligibility-')])["+i+"]")).isDisplayed());
        }
    }
}

