package Stepdefinition;


import Modules.Commons;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.v85.dom.model.SetChildNodes;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class Hooks {
    public static WebDriver driver;
    private static HashMap<Integer,String> scenarios;
    Logger logger = LoggerFactory.getLogger(Hooks.class);

    public Hooks(){
        if(scenarios == null)
            scenarios = new HashMap<Integer,String>();
    }

    @Before
    public void i_open_chrome_browser(Scenario scenario) {
        beforeScenario();
        addScenario(scenario.getName());
    }

    private void addScenario(String scenario){
        Thread currentThread = Thread.currentThread();
        int threadID = currentThread.hashCode();
        scenarios.put(threadID,scenario);
    }

    public synchronized String getScenario() {
        Thread currentThread = Thread.currentThread();
        int threadID = currentThread.hashCode();
        return scenarios.get(threadID);
    }

    @After
    public void i_close_chrome_browser() {
        close();
    }

    public void beforeScenario() {
        DesiredCapabilities caps = new DesiredCapabilities();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    public void close() {
        driver.close();
        driver.quit();
    }

    public void clickJS(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void scrollTo(WebElement element) throws InterruptedException {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(1000);
    }

    public void scrollToTheEnd() throws InterruptedException {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(1000);
    }

    public void scrollBy(int pixels) throws InterruptedException {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.scrollBy(0," + pixels + ")", "");
        Thread.sleep(1000);
    }

    public void refresh() {
        driver.navigate().refresh();
    }

}
