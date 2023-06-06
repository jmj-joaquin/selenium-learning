package guru99Application;

import static org.testng.Assert.assertEquals;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.annotations.AfterSuite;

public class Guru99ApplicationTest {

	ChromeDriver driver;

	String url = "http://demo.guru99.com/v4";

	// valid for 20 days from 10/3/23
	String userIdValue = "mngr484236";
	String userPasswordValue = "Enemepa";

	protected String getRandString() {
		String RANDCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder rand = new StringBuilder();
		Random rnd = new Random();
		while (rand.length() < 3) { // length of the random string.
			int index = (int) (rnd.nextFloat() * RANDCHARS.length());
			rand.append(RANDCHARS.charAt(index));
		}
		String randStr = rand.toString();
		return randStr;
	}

	String randEmail = getRandString() + "@gmail.com";

	@BeforeSuite
	public void invokeBrowser() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get(url);
	}

	@Test(priority = 0)
	public void verifyTitleOfThePage() {
		String expectedTitle = "Guru99 Bank Home Page";
		String actualTitle = driver.getTitle();

		assertEquals(actualTitle, expectedTitle);
	}

	@Test(priority = 10)
	public void verifyLoginToGuru99Application() {
		WebElement userId = driver.findElement(By.name("uid"));
		WebElement userPassword = driver.findElement(By.name("password"));
		WebElement loginButton = driver.findElement(By.name("btnLogin"));

		userId.sendKeys(userIdValue);
		userPassword.sendKeys(userPasswordValue);
		loginButton.click();
	}

	@Test(priority = 20)
	public void addCustomer() {
		//verifyLoginToGuru99Application();

		WebElement addCustomerLink = driver.findElement(By.linkText("New Customer"));

		addCustomerLink.click();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.xpath("//input[@value='f']")).click();
		driver.findElement(By.name("name")).sendKeys("Saurabh Dhingra");
		driver.findElement(By.name("dob")).sendKeys("06/06/1989");
		driver.findElement(By.name("addr")).sendKeys("Gurgaon");
		driver.findElement(By.name("city")).sendKeys("Gurugram");
		driver.findElement(By.name("state")).sendKeys("Haryana");
		driver.findElement(By.name("pinno")).sendKeys("122001");
		driver.findElement(By.name("telephoneno")).sendKeys("97834523576");
		driver.findElement(By.name("emailid")).sendKeys(randEmail);
		driver.findElement(By.name("password")).sendKeys("poiy@123");
		driver.findElement(By.name("sub")).click();
	}

	@Test(priority = 30)
	public void getCustomerId() {
		//addCustomer();

		String customerId = driver.findElement(By.xpath("//table[@id='customer']/tbody/tr[4]/td[2]")).getText();

		System.out.println("Customer Id: " + customerId);
	}

	@AfterSuite
	public void closeBrowser() {
		// driver.close();

		driver.quit();
	}

}
