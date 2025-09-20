package evasel;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenStudentFormTests {
	
WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://demoqa.com/automation-practice-form");
		driver.manage().window().maximize();
		
	}
	
	@Test(dataProvider="getData")
	public void formTest(String fname, String lname, String email, String gender, String phoneNo) throws InterruptedException {
		
	driver.findElement(By.id("firstName")).sendKeys(fname);
	driver.findElement(By.id("lastName")).sendKeys(lname);
	driver.findElement(By.id("userEmail")).sendKeys(email);
	driver.findElement(By.xpath("//label[text()='" + gender + "']")).click();
	driver.findElement(By.id("userNumber")).sendKeys(phoneNo);
	
	WebElement ele = driver.findElement(By.id("dateOfBirthInput"));
	ele.clear();
	ele.sendKeys("04 Sep 2025");
	//driver.findElement(By.id("dateOfBirthInput")).click();
	
	Thread.sleep(1000); 
	
	JavascriptExecutor js = (JavascriptExecutor)driver;
	WebElement upic = driver.findElement(By.id("uploadPicture"));
	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", upic);
	
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	WebElement subjectInput = driver.findElement(By.id("subjectsInput"));
    subjectInput.sendKeys("Computer Science");
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@id,'react-select') and text()='Computer Science']"))).click();
    Thread.sleep(500);
    driver.findElement(By.xpath("//label[text()='Music']")).click();
    Thread.sleep(500);
    driver.findElement(By.id("uploadPicture")).sendKeys("C:\\Users\\RKC-Nagaur\\Downloads\\img.png");
    Thread.sleep(500);
	driver.findElement(By.id("currentAddress")).sendKeys("Bangalore");
	
	js.executeScript("window.scrollBy(0, 300);");
	
	driver.findElement(By.id("state")).click();
    WebElement opt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Rajasthan']")));
    opt.click();

    driver.findElement(By.id("city")).click();
    WebElement opt2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Jaipur']")));
    opt2.click();
	
	//driver.findElement(By.id("submit")).click();
	
	WebElement elem = driver.findElement(By.id("submit"));
	js.executeScript("arguments[0].scrollIntoView(true);", elem);
	Thread.sleep(500); 
	elem.click();
		
	WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(15));
	WebElement confmod = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-content")));

	WebElement chknm = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='Student Name']/following-sibling::td")));
	WebElement chkemail = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='Student Email']/following-sibling::td")));

	/*Assert.assertEquals(chknm.getText().equals(fname + " " + lname), "Name Matches");
	Assert.assertEquals(chkemail.getText().equals(email), "Email matchees");*/
	
	Assert.assertTrue(chknm.getText().equals(fname + " " + lname));
	Assert.assertTrue(chkemail.getText().equals(email));

	}
	
	@DataProvider(name="getData")
	public Object[][] getTheData() {
		
		return new Object[][] {
			
			{"marco", "flint", "flinto@gmail.com", "Male", "1234567890"},
			{"cicero", "aurelius", "aurora@gmail.com", "Male", "0987654321"}
			
		};

	};
	
	@AfterMethod
	public void close() {
		
		driver.quit();
		
	}
	

}
