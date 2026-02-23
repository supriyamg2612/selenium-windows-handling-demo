package com.selenium.windowhandling;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WindowHandlingDemo {

	static WebDriver driver ;
	public static void main(String[] args) {
		
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
        WebDriverWait myWait = new WebDriverWait(driver, Duration.ofSeconds(4));
        driver.get("https://the-internet.herokuapp.com/windows");
		driver.manage().window().maximize();
        String parentWindow = driver.getWindowHandle();
        System.out.println("handle of parent window"+parentWindow );
        
        WebElement linkElement = myWait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Click Here"))); //use
         linkElement.click();

         
         Set<String> allWindows = driver.getWindowHandles();
         
         for(String childWindow: allWindows) {
        	 if(!childWindow.equals(parentWindow)) {
        		 driver.switchTo().window(childWindow);
        		 break;
        	 }
         }
         System.out.println("Child Window Title: " + driver.getTitle());
         driver.close();  // close child window
         driver.switchTo().window(parentWindow);
         System.out.println("Back to Parent Window Title: " + driver.getTitle());

         driver.quit();
         driver.close();
	}
}
