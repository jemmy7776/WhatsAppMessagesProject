package com.datadriven.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.excel.utility.Xls_Reader;

public class DataDrivenTest {
	public static void main(String[] args) throws InterruptedException  {
		
	
	System.setProperty("webdriver.chrome.driver","C:\\Users\\jasha\\Documents\\eclipse-workspace\\chromedriver.exe");
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	
	WebDriverWait wait = new WebDriverWait(driver,30);
	Xls_Reader reader = new Xls_Reader("C:\\Users\\jasha\\Documents\\eclipse-workspace\\WhatsAppMessages\\src\\com\\testdata\\WhatsApp Messages.xlsm");
	String mobile="";
	int totalrows= reader.getRowCount("Test");
	System.out.println(totalrows);
	reader.addColumn("Test", "Status");
	
	for(int i=2; i<=totalrows;i++) {
	
		try {
		String s =reader.getCellData("Test", "mobile", i);
		mobile = s.replaceAll("^.|.$","");
		
		String name = reader.getCellData("Test", "name", i);
		String username = reader.getCellData("Test", "username", i);
		String password = reader.getCellData("Test", "password", i);
		String message1 = "Dear "+name+", ";
		String message2	= "Thanks for joining Aptence, your login credentials are:";
		String message3 = "Username: "+username+" ";
		String message4 = "Password: "+password+" ";
		String message5 = "App Download Link: aptence.app.link ";
		String message6 = "Web: www.aptence.com";
		String message7 = "Thanks, ";
		String message8 = "Team Aptence ";
		String keysPressed =  Keys.chord(Keys.SHIFT, Keys.RETURN);
		
	//	reader.setCellData("Test", "Status", i, "Pass");
		
		driver.get("https://api.whatsapp.com/send?phone=91"+mobile);
	
		WebElement action=driver.findElement(By.id("action-button"));
		wait.until(ExpectedConditions.elementToBeClickable(action));
		action.click();
		WebElement whatsappweb = driver.findElement(By.xpath("//a[text()='use WhatsApp Web']"));
		wait.until(ExpectedConditions.elementToBeClickable(whatsappweb));
		whatsappweb.click();
		
		WebElement menubtn = driver.findElement(By.xpath("//span[@data-icon='menu']"));
		wait.until(ExpectedConditions.visibilityOf(menubtn));
		
		if(driver.findElements(By.xpath("//div[@data-tab='1' and //div[@dir='ltr']]")).size()>0) {
		WebElement message = driver.findElement(By.xpath("//div[@data-tab='1' and //div[@dir='ltr']]"));
		//Thread.sleep(12000);
		wait.until(ExpectedConditions.elementToBeClickable(message));
		message.click();
		message.sendKeys(message1);
		message.sendKeys(keysPressed);
		message.sendKeys(message2);
		message.sendKeys(keysPressed);
		message.sendKeys(message3);
		message.sendKeys(keysPressed);
		message.sendKeys(message4);
		message.sendKeys(keysPressed);
		message.sendKeys(message5);
		message.sendKeys(keysPressed);
		message.sendKeys(message6);
		message.sendKeys(keysPressed);
		message.sendKeys(message7);
		message.sendKeys(keysPressed);
		message.sendKeys(message8);
		message.sendKeys(keysPressed);
		
		driver.findElement(By.xpath("//span[@data-icon='send']")).click();
		 try{   
			   driver.switchTo().alert().dismiss();  
			  }
		 catch(Exception e){ 
			   System.out.println("unexpected alert not present");   
			  }
		Thread.sleep(4000);
		}
		else
		{
		  System.out.println(mobile+" does not exist in Whatsapp");
		  reader.setCellData("Test", "Status", i, "Fail");
		} 
		}
		catch(Exception error){
		System.out.println(error.getMessage());
		System.out.println("Error on Mobile---------------------- "+mobile);
		
	}
	}
	}
}	

	


