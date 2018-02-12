package automation;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HeadlessCanaryTest {
	
	public static void main(String[] args) throws InterruptedException, MalformedURLException {
		
			//String nodeUrl = "http://10.5.21.39:5555/wd/hub"; // not work on windows 7 
			//String nodeUrl = "http://10.5.21.38:5555/wd/hub"; //works on windows 10 only
			String nodeUrl = "http://10.5.21.35:5555/wd/hub"; //windows 8 works
			
			DesiredCapabilities capability = DesiredCapabilities.chrome();
			ChromeOptions co = new ChromeOptions();
			co.addArguments("headless");			
			capability.setCapability(ChromeOptions.CAPABILITY, co);
			
			WebDriver driver = new RemoteWebDriver(new URL(nodeUrl), capability);
 
			String baseUrl = "http://www-qa.ticketmonster.co.kr/deal/468852238"; // out of stock

	    	driver.get(baseUrl);
	    	
	    	String stockAvailable;
	    	
	    	String available = driver.findElement(By.xpath("//*[@id='buy_button']")).getText();
	    	System.out.println(available);
	    	
	    	if(available.equalsIgnoreCase("바로구매")) {
	    		stockAvailable = "Y";
	    	} else {
	    		stockAvailable = "N";
	    	}
	    	
	    	System.out.println(stockAvailable);	
	    	
	    	driver.close();
	}
  
}