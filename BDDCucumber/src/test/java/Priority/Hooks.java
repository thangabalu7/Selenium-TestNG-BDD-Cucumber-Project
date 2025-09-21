package Priority;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import cucumber.api.java.BeforeStep;
import driverPackage.Browsersetup;



public class Hooks {

	private Browsersetup setup;
	

//	The reason the Hooks constructor is called at runtime without you manually passing a Browsersetup object 
//	is because Cucumber uses Dependency Injection (DI) through a DI library, typically PicoContainer, 
//	which automatically creates class instances and injects dependencies.
	public Hooks(Browsersetup setup) {
		this.setup = setup;
	}

	@Before
	public void beforeScenario(Scenario scenarios) {
		// this method will execute before the scenario
		System.out.println("beforeScenario : " + scenarios.getName());
		String browser = "Chrome";
		switch (browser) {
		case "Chrome":
			RemoteWebDriver driver = new ChromeDriver();
			setup.setDriver(driver);
			System.out.println("Run by -------->" + browser);
			break;
		case "Firefox":
			driver = new FirefoxDriver();
			
			break;
		case "Edge":
			driver = new EdgeDriver();
			break;
		default:
			System.out.println("Unable to Find Browser: " + browser);

		}
		WebDriverWait wait = new WebDriverWait(setup.getDriver(), Duration.ofSeconds(10));
		setup.setWait(wait);
	}

	@After
	public void afterScenario(Scenario scenarios) throws IOException, InterruptedException {

		boolean status = scenarios.isFailed();
		if (scenarios.getSourceTagNames().contains("@Test")) {

			Thread.sleep(2000);
			setup.getDriver().findElement(By.cssSelector(
					"body > app-root:nth-child(1) > app-nav-bar:nth-child(1) > mat-toolbar:nth-child(1) > mat-toolbar-row:nth-child(1) > div:nth-child(3) > button:nth-child(2) > mat-icon:nth-child(2)"))
					.click();
			Thread.sleep(2000);
			setup.getDriver().findElement(By.xpath("//span[normalize-space()='Clear cart']")).click();
			byte[] screenshotAs = setup.getDriver().getScreenshotAs(OutputType.BYTES);
			scenarios.embed(screenshotAs, "image/png");
		}
		if (status) {
			File fls = setup.getDriver().getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(fls,
					new File("C:\\Users\\thangrs\\eclipse-workspace\\BDDCucumber" + scenarios.getName() + "alpha.png"));
			byte[] screenshotAs = setup.getDriver().getScreenshotAs(OutputType.BYTES);
			scenarios.embed(screenshotAs, "image/png");

		}
		System.out.println(scenarios.getStatus());// This function give status of method(passed,failed,skippped) if we
													// use getStatus in before annotation in throw "Undefined" status
		System.out.println(scenarios.getId());
		System.out.println(scenarios.getLines());// This method tell which scenario file line is executing if we use
												// Scenario Outline it tell which example is executing
		System.out.println(scenarios.getUri());// This method tell location of feature file
		setup.getDriver().quit();
	}

	@BeforeStep
	public void beforeEveryStep(Scenario scenarios) {
		// this method will execute before to the every step
		//System.out.println(" beforeEveryStep : " + scenarios.getName());
	}

	@AfterStep
	public void afterEveryStep(Scenario scenarios) {
		// this method will execute after to the every step
	//	System.out.println("afterEveryStep : " + scenarios.getName());
	}

}
