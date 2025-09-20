package evasel;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InteractionTests {
	
WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		
		driver = new ChromeDriver();
		driver.get("https://demoqa.com/alerts");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}
	
	@Test(groups="smoke")
	public void test2() throws InterruptedException {

		driver.get("https://demoqa.com/alerts");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		WebElement ele = driver.findElement(By.id("confirmButton"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		Thread.sleep(500); 
		ele.click();
		
		driver.switchTo().alert().accept();
		
		String verifyTxt = driver.findElement(By.className("text-success")).getText();
		Assert.assertTrue(verifyTxt.contains("You selected"));
		
		Thread.sleep(2000);

		driver.findElement(By.xpath("//span[text()='Frames']")).click();
		
		WebElement iframe = driver.findElement(By.id("frame1"));
				
		driver.switchTo().frame(iframe);
		
		String readTxt = driver.findElement(By.xpath("//h1[@id='sampleHeading']")).getText();
		Assert.assertEquals(readTxt, "This is a sample page");
		driver.switchTo().defaultContent();
		
		
	}
	
	@AfterMethod
	public void close() {
		
		driver.quit();
		
	}

}
