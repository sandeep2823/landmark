package stepDefinition;

import java.util.List;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class LandingPage {
	public static Logger logger = LogManager.getLogger(LandingPage.class);
	WebDriver driver = null;

	@Before
	public void setup() {
		String projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectPath + "/src/test/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-infobars");
		options.addArguments("--disable-extensions");
		driver.manage().window().maximize();
	}

	@After
	public void teardown() {
		driver.quit();
	}

	@Given("user is on landing page")
	public void user_is_on_landing_page() {
		logger.info("Navigating to coinmarketcap landing page");
		driver.get("https://coinmarketcap.com/");

	}

	@And("user verify {int} results are displayed")
	public void user_verify_results_are_displayed(int int1) {
		int rowCount = driver.findElements(By.xpath("//table[contains(@class,'table')]/tbody/tr")).size();
		System.out.println("number of results present in the page is " + rowCount);
	}

	@Given("user login successfully")
	public void user_login_successfully() throws InterruptedException {
		driver.findElement(By.xpath("//div/button[text()='Log In']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("food.chatbot1990@gmail.com");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("sandeeptest1");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[contains(@class,'modalOpened')]//button[text()='Log In']")).click();
		Thread.sleep(5000);
	}

	@Given("I hover over on the {string} tab")
	public void i_hover_over_on_the_tab(String tabs) throws InterruptedException {
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.linkText(tabs))).perform();
		Thread.sleep(2000);
	}

	@When("I click on {string} option")
	public void i_click_on_option(String ranking) {
		// Write code here that turns the phrase above into concrete actions
		driver.findElement(By.linkText(ranking)).click();
	}

	@When("I record the data")
	public void i_record_the_data() throws InterruptedException {
		// No.of Columns
		Thread.sleep(2000);
		List<WebElement> col = driver.findElements(By.xpath("//table[contains(@class,'table')]/thead/tr/th"));
		// No.of rows
		List<WebElement> rows = driver.findElements(By.xpath("//table[contains(@class,'table')]/tbody/tr"));
		System.out.println("No of cols are : " + col.size());
		System.out.println("No of rows are : " + rows.size());

		// Capturing all the name in the result list
		List<WebElement> names = driver
				.findElements(By.xpath("//table[contains(@class,'table')]/tbody/tr/td[3]/div/a/div/div/p"));
		Iterator<WebElement> iterator = names.iterator();
		while (iterator.hasNext()) {
			String name = iterator.next().getText();
			System.out.println(name);
		}
	}

	@When("I select filters button")
	public void i_select_filters_button() throws InterruptedException {
		driver.findElement(By.xpath("//div[contains(@class,'table-control-area')]/div/button")).click();
		Thread.sleep(2000);
	}

	@When("I select {string} filter from {string} dropdown")
	public void i_select_filter_from_dropdown(String value, String dropdown) throws InterruptedException {
		driver.findElement(By.xpath("//li[@class='filter']/div/span/button[text()='" + dropdown + "']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//li[text()='" + value + "']")).click();
		Thread.sleep(2000);
	}
}
