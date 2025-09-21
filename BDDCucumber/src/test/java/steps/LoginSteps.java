package steps;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;



public class LoginSteps {

	WebDriver driver;

	// Scenaior 1 "Login successfull"
	@Given("User should navigate to application")
	public void userShouldNavigateToApplication() {
		driver = new ChromeDriver();
		driver.get("https://bookcart.azurewebsites.net/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@Given("User clicks on the login link")
	public void userClicksOnTheLoginLink() {
		driver.findElement(By.xpath("//button[@mattooltip='Login']")).click();

	}

	@Given("User enter the username as {string}")
	public void userEnterTheUsernameAs(String userName) {
		driver.findElement(By.cssSelector("input[formcontrolname='username']")).sendKeys(userName);

	}

	@Given("User enter the password as {string}")
	public void userEnterThePasswordAs(String passWord) {
		driver.findElement(By.cssSelector("input[formcontrolname='password']")).sendKeys(passWord);
	}

	@When("User click the login button")
	public void userClickTheLoginButton() {
		driver.findElement(By.xpath("(//button[@color='primary'])[3]")).click();

	}

	@Then("login should be success")
	public void loginShouldBeSuccess() {
		String textOfUserName = driver.findElement(By.cssSelector(
				"a[class='mat-mdc-menu-trigger mdc-button mdc-button--unelevated mat-mdc-unelevated-button mat-primary mat-mdc-button-base ng-star-inserted'] span[class='mdc-button__label'] span"))
				.getText();
		System.out.println(textOfUserName);
		driver.quit();

	}

	// Scenario 2 "Log in should be failed "
	@Then("login should be failed")
	public void loginShouldBeFailed() {
		System.out.println("Login failed");
		driver.quit();
	}

}
