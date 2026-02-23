package com.selenium.windowhandling;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MultipleWindowsHandlingDemo {
	
	static WebDriver driver ;
	public static void main(String[] args) {
		
		int childCount = 0;

		
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
        WebDriverWait myWait = new WebDriverWait(driver, Duration.ofSeconds(4));

        
        driver.get("https://the-internet.herokuapp.com/windows");
		driver.manage().window().maximize();
        String parentWindow = driver.getWindowHandle();
        System.out.println("handle of parent window"+parentWindow );
        
        // Open multiple windows 
        WebElement linkElementOne = myWait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Click Here"))); //use
        linkElementOne.click();
        WebElement linkElementTwo = myWait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Elemental Selenium"))); //use
        linkElementTwo.click();
        
        // Get all window handles

        Set<String> allWindows = driver.getWindowHandles();
        
        // Convert Set to List for ordered access
        List<String> windowList = new ArrayList<>(allWindows);

        
        for(String childWindows:allWindows) {
        	if(!childWindows.equals(parentWindow)){
        		childCount++;
        		driver.switchTo().window(childWindows);
        		System.out.println("Window  Title: " + driver.getTitle());
                System.out.println("Window URL: " + driver.getCurrentUrl());
        		
       
        
       // Perform action on 2nd child window
        if (childCount == 2) {
        	System.out.println("=== Performing Action on 2nd Child Window ===");
             driver.findElement(By.xpath("//button[text()='Take me to the tips! ']")).click();
     		System.out.println("2nd child window action Title: " + driver.getTitle());
        }
     		driver.close();
}
}
        
        
        driver.switchTo().window(parentWindow);
        System.out.println("Back to Parent Window Title: " + driver.getTitle());
        
        driver.quit();

	}
}

        
        
        
