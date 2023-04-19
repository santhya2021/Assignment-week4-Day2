package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class Amazon_Assignment {

	public static void main(String[] args) throws  InterruptedException, IOException {
		// TODO Auto-generated method stub
		ChromeOptions apt=new ChromeOptions();
		apt.addArguments("--disable-notifications");
		ChromeDriver driver  = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("oneplus 9 pro");
		driver.findElement(By.xpath("//span[@id='nav-search-submit-text']/input")).click();
		WebElement findElement = driver.findElement(By.xpath("//span[@class='a-price-whole']"));
		String text = findElement.getText();
		System.out.println("Mobile Price:"+text);
		Thread.sleep(2000);
		WebElement findElement2 = driver.findElement(By.xpath("//span[text()='3.0 out of 5 stars']"));
		Actions hover=new Actions(driver);
		Thread.sleep(2000);
		hover.moveToElement(findElement2).click().perform();
		String text2 = driver.findElement(By.xpath("a-icon-row a-spacing-small a-padding-none")).getText();
		System.out.println("Customer Ratings:"+text2);
		driver.findElement(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']")).click();
		//new window
		Set<String> windowHandles1 = driver.getWindowHandles();
		List<String> obj1=new ArrayList<String>(windowHandles1);
		driver.switchTo().window(obj1.get(1));
		Thread.sleep(3000);
		
		//snapshot
		File src1 = driver.getScreenshotAs(OutputType.FILE);
		File des= new File("./snaps/screenshot.png");
		FileUtils.copyFile(src1, des);
		
		driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
		
		WebElement findElement3 = driver.findElement(By.xpath("//span[@id='attach-accessory-cart-subtotal']"));
		String text3 = findElement3.getText();
		if(text.equals(text3))
		{
			System.out.println("The sub total is verified and matched");
		}
		else
		{
			System.out.println("The sub total is verified and un-matched");
		}
		
		driver.quit();
		

	}

}
