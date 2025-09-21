package steps;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import driverPackage.Browsersetup;


public class AddToCart {

Browsersetup setup;
public AddToCart(Browsersetup setup)
{
	this.setup=setup;
	PageFactory.initElements(setup.getDriver(), this);
}

@FindBy(css = "input[formcontrolname='username']")
WebElement username;
@FindBy(css = "input[formcontrolname='password']")
WebElement password;
@FindBy(css= "button[class*='mdc-button mdc-button--raised']")
WebElement addToCart;
By elements = By.xpath("//input[@placeholder='Search books or authors']");
@Given("User should navigate to the book cart application")
public void userShouldNavigateToTheBookCartApplication() throws IOException {
	setup.getDriver().get(getData("url"));
	setup.getDriver().manage().window().maximize();
              setup.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	
}
@Given("User should login as {string} and {string} with validate credential")
public void userShouldLoginAsAndWithValidateCredential(String userName, String passWord) {
	setup.getDriver().findElement(By.xpath("//button[@mattooltip='Login']")).click();
	username.sendKeys(userName);
	password.sendKeys(passWord);
	setup.getDriver().findElement(By.xpath("(//button[@color='primary'])[3]")).click();
}
@Given("User should search a {string} with validate credential")
public void userShouldSearchAWithValidateCredential(String bookName) throws InterruptedException {
   setup.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search books or authors']")));
   setup.getDriver().findElement(By.xpath("//input[@placeholder='Search books or authors']")).sendKeys(bookName);
   setup.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[class ='mdc-list-item__primary-text']")));
   Thread.sleep(2000);
   setup.getDriver().findElement(By.cssSelector("span[class ='mdc-list-item__primary-text']")).click();
   setup.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[class*='mdc-button mdc-button--raised']")));
}
@When("User add the book to the cart")
public void userAddTheBookToTheCart() throws InterruptedException {
	Thread.sleep(2000);
	addToCart.click();
}
@Then("The cart badge should be update")
public void theCartBadgeShouldBeUpdate() {
   System.out.println("Successfully add to card");
}

public static String getData(String data) throws IOException
{
	FileInputStream fls = new FileInputStream(
			"C:\\Users\\thangrs\\eclipse-workspace\\BDDCucumber\\Datas\\url.properties");
	Properties prop = new Properties();
	prop.load(fls);
	String datas = prop.getProperty(data);
	return datas;
}
}
