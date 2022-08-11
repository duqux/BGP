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

public class BusinessImpact extends Hooks {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    Actions action = new Actions(driver);
    JSONParser parser = new JSONParser();
    Object obj;
    Logger logger = LoggerFactory.getLogger(BusinessImpact.class);
    String scenario = getScenario();

    {
        try {
            obj = parser.parse(new FileReader("src/test/java/Data/businessImpact.json"));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    JSONObject jsonObject = (JSONObject) obj;

    String overseasSale =  (String) jsonObject.get("Overseas Sales");
    String overseasSale1 =  (String) jsonObject.get("Overseas Sales 1");
    String rationale =  (String) jsonObject.get("Rationale for Projections");
    String benefits =  (String) jsonObject.get("Non Tangible Benefits");



    public void fillInBusinessImpact(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR,7);
        Date oneWeekFromToday = calendar.getTime();
        String formattedFutureDate = sdf.format(oneWeekFromToday);

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h2[contains(text(),'Explain The Business Impact')]")));
        WebElement fyEndDateTextBox = driver.findElement(By.xpath("//input[@id='react-project_impact-fy_end_date_0']"));
        WebElement firstOverseasSalesTextBox = driver.findElement(By.xpath("//input[@id='react-project_impact" +
                "-overseas_sales_0']"));
        WebElement secondOverseasSalesTextBox = driver.findElement(By.xpath("//input[@id='react-project_impact" +
                "-overseas_sales_1']"));
        WebElement thirdOverseasSalesTextBox = driver.findElement(By.xpath("//input[@id='react-project_impact" +
                "-overseas_sales_2']"));
        WebElement fourthOverseasSalesTextBox = driver.findElement(By.xpath("//input[@id='react-project_impact" +
                "-overseas_sales_3']"));
        WebElement firstOverseasInvestmentsTextBox = driver.findElement(By.xpath("//input[@id='react-project_impact-overseas_investments_0']"));
        WebElement secondOverseasInvestmentsTextBox = driver.findElement(By.xpath("//input[@id='react-project_impact" +
                "-overseas_investments_1']"));
        WebElement thirdOverseasInvestmentsTextBox = driver.findElement(By.xpath("//input[@id='react-project_impact" +
                "-overseas_investments_2']"));
        WebElement fourthOverseasInvestmentsTextBox = driver.findElement(By.xpath("//input[@id='react-project_impact" +
                "-overseas_investments_3']"));
        WebElement rationaleTextBox = driver.findElement(By.xpath("//textarea[@id='react-project_impact-rationale_remarks']"));
        WebElement benefitsTextBox = driver.findElement(By.xpath("//textarea[@id='react-project_impact" +
                "-benefits_remarks']"));

        fyEndDateTextBox.sendKeys(formattedFutureDate);
        firstOverseasSalesTextBox.sendKeys(overseasSale1);
        secondOverseasSalesTextBox.sendKeys(overseasSale);
        thirdOverseasSalesTextBox.sendKeys(overseasSale);
        fourthOverseasSalesTextBox.sendKeys(overseasSale1);
        firstOverseasInvestmentsTextBox.sendKeys(overseasSale);
        secondOverseasInvestmentsTextBox.sendKeys(overseasSale);
        thirdOverseasInvestmentsTextBox.sendKeys(overseasSale1);
        fourthOverseasInvestmentsTextBox.sendKeys(overseasSale1);
        rationaleTextBox.sendKeys(rationale);
        benefitsTextBox.sendKeys(benefits);

    }
}
