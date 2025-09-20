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

public class FormTests {
	
	WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		
		driver = new ChromeDriver();
		
	}

	@Test(groups="smoke")
	public void test1() throws InterruptedException {
		
		driver.get("https://demoqa.com/text-box");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.id("userName")).sendKeys("demouser");
		driver.findElement(By.id("userEmail")).sendKeys("demoEmail@gmail.com");
		driver.findElement(By.id("currentAddress")).sendKeys("Bangalore");
		driver.findElement(By.id("permanentAddress")).sendKeys("Karnataka");

	    WebElement subBtn = driver.findElement(By.id("submit"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", subBtn);
		
		WebElement vernm = driver.findElement(By.id("name"));
        WebElement verem = driver.findElement(By.id("email"));

        Assert.assertTrue(vernm.getText().contains("demouser"), "Name output mismatch");
        Assert.assertTrue(verem.getText().contains("demoEmail@gmail.com"), "Email output mismatch");
		
		// the actual text on the website is different from what is provided in the problem statement :)
		
	}
	
	@Test(groups="smoke")
	public void test2() throws InterruptedException {
		
		driver.get("https://demoqa.com/checkbox");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.xpath("//button[@title='Expand all']")).click();
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    WebElement doclab = driver.findElement(By.xpath("//label[@for='tree-node-documents']"));
	    js.executeScript("arguments[0].click();", doclab);
	    String confSel = driver.findElement(By.id("result")).getText();
	    Assert.assertTrue(confSel.toLowerCase().contains("documents"), "Expected 'documents' to be selected.");
		
	}
	
	@AfterMethod
	public void close() {
		
		driver.quit();
		
	}	
		
}
