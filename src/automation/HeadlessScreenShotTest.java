package automation;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class HeadlessScreenShotTest {
	@Test
	public void main() throws InterruptedException {
  
			String baseUrl;
	    	baseUrl = "http://svc.tmon.co.kr/login?redirect=%2F";

	    	ChromeOptions co = new ChromeOptions();
	    	//co.setBinary("C:/webdriver/chromedriver.exe");
	    	co.setBinary("C:/Users/mulder/AppData/Local/Google/Chrome SxS/Application/chrome.exe");
	    	//co.addArguments("start-maximized");
	    	co.addArguments("window-size=1280x1024");
	    	co.addArguments("--headless");
	    	
	    	WebDriver driver = new ChromeDriver(co);
	    	WebDriverWait wait = new WebDriverWait(driver, 10);
	    	
	    	driver.get(baseUrl);
	    	
	    	driver.findElement(By.xpath("//*[@id='user']")).sendKeys("mulder");
	    	driver.findElement(By.xpath("//*[@id='passwd']")).sendKeys("Ghkdtkseocjq012!");
	    	
	    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='login-box']/a")));
	    	driver.findElement(By.xpath("//*[@id='login-box']/a")).click();
	    	
	    	Thread.sleep(1000);
	    	
	    	driver.get("http://svc.tmon.co.kr/go/73"); //
	    	
	    	Thread.sleep(5000);
	    	
	    	LocalDate localDate = LocalDate.now();
	    	String formattedLocalDate = DateTimeFormatter.ofPattern("yyy/MM/dd").format(localDate);
	    	String fileName = "D:/screenCapture/backup/" + formattedLocalDate.replace("/", "").toString() + ".png";
	    	//System.out.println(fileName);
	    	
	    	//Screenshot
	    	File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    	//System.out.println(srcFile.getAbsolutePath());
	    	try {
	    		FileUtils.copyFile(srcFile, new File(fileName));
	    	} catch (IOException e) {
	    		e.printStackTrace();
	    	}
	    	
	    	driver.close();
	}
  
}