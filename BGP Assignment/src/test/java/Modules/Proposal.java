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

import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

public class Proposal extends Hooks {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    Actions action = new Actions(driver);
    JSONParser parser = new JSONParser();
    Object obj;
    Logger logger = LoggerFactory.getLogger(Proposal.class);
    String scenario = getScenario();

    {
        try {
            obj = parser.parse(new FileReader("src/test/java/Data/proposal.json"));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    JSONObject jsonObject = (JSONObject) obj;
    String title = (String) jsonObject.get("Project Title");
    String projectDescription = (String) jsonObject.get("Project Description");
    String activity = (String) jsonObject.get("Activity");
    String targetMarket = (String) jsonObject.get("Target Market");


    public void fillInProposal(){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h2[contains(text(),'Submit Your Proposal')]")));
        WebElement projectTitleTextBox = driver.findElement(By.xpath("//input[@id='react-project-title']"));
        WebElement startDateTextBox = driver.findElement(By.xpath("//input[@id='react-project-start_date']"));
        WebElement endDateTextBox = driver.findElement(By.xpath("//input[@id='react-project-end_date']"));
        WebElement projectDescriptionTextBox = driver.findElement(By.xpath("//textarea[@id='react-project-description']"));
        WebElement activityDropDownList = driver.findElement(By.xpath("//span[@id='react-select-project-activity" +
                "--value']"));
        WebElement targetMarketDropDownList = driver.findElement(By.xpath("//span[@id='react-select-project-primary_market--value']"));
        WebElement targetMarketYesRadioButton = driver.findElement(By.xpath("//input[@id='react-project" +
                "-is_first_time_expand-true']"));

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR,7);
        Date oneWeekFromToday = calendar.getTime();
        String formattedCurrentDate = sdf.format(date);
        String formattedFutureDate = sdf.format(oneWeekFromToday);

        projectTitleTextBox.sendKeys(title);
        startDateTextBox.sendKeys(formattedCurrentDate);
        endDateTextBox.sendKeys(formattedFutureDate);
        projectDescriptionTextBox.sendKeys(projectDescription);
        activityDropDownList.click();
        WebElement activityText = driver.findElement(By.xpath("//div[contains(text(),'"+activity+"')]"));
        activityText.click();
        targetMarketDropDownList.click();
        WebElement targetMarketText = driver.findElement(By.xpath("//div[contains(text(),'"+targetMarket+"')]"));
        targetMarketText.click();
        targetMarketYesRadioButton.click();

    }
}
