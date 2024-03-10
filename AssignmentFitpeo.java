package miscellaneous;

import io.github.bonigarcia.wdm.WebDriverManager;


import java.time.Duration;
import org.openqa.selenium.WebElement;
 import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.junit.Assert;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

public class AssignmentFitpeo {

	public static void main(String[] args) throws InterruptedException
	{
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new FirefoxDriver();
//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver();
//		WebDriverManager.edgedriver().setup();
//		WebDriver driver = new EdgeDriver();
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
		// Open Flipkart Website
		driver.get("https://www.flipkart.com/");

		String homepage = driver.getTitle();
		System.out.println(homepage);
		String message = "Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!";
		
		//homepage loads successfully.
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.urlMatches("https://www.flipkart.com"));
		
		Assert.assertTrue(message.contains("Online Shopping Site "));
		System.out.println("Title is correct");
		
		//Search and Add to Cart: 
		WebElement searchbox = driver.findElement(By.xpath("//input[@class='Pke_EE']"));
		searchbox.sendKeys("laptop");
		
		Thread.sleep(5000);
		List<WebElement> a = driver.findElements(By.xpath("//ul[@class='_1sFryS _2x2Mmc _3ofZy1']"));
		
		for(WebElement element : a)
		{
			if(element.getText().contains("laptop stand"))
			{
			element.click();
			}
		}
		
		
		WebElement product = driver.findElement(By.xpath("//a[@title='Bitline Aluminum Alloy Adjustable, Portable, Foldable, Ergonomic, Tablet Laptop Stand_01 Laptop Stand']"));
		product.click();
		
		Thread.sleep(5000);
//		Proceed to Checkout:

		Set<String> s = driver.getWindowHandles();
		Iterator<String> it = s.iterator();
		
		String parentwindow = it.next();
		String childwindow = it.next();
		
		driver.switchTo().window(childwindow);
		driver.findElement(By.xpath("//button[@class='_2KpZ6l _2U9uOA _3v1-ww']")).click();
		
		WebElement cartproduct =  driver.findElement(By.xpath("//a[@class='_2Kn22P gBNbID']"));
		
		Thread.sleep(5000);
		Assert.assertTrue(cartproduct.getText().contains("Bitline Aluminum Alloy Adjustable"));
		System.out.println("Cartproduct matched successfully");
		
		driver.findElement(By.xpath("//span[normalize-space()='Place Order']")).click();
		
//		User Authentication:
//			On the login page, enter valid credentials (username and password) to log in.
//			Verify that the user is successfully logged in.
		
		Thread.sleep(5000);

		WebElement mobile  = driver.findElement(By.xpath("//input[@type='text']"));
		mobile.sendKeys("paviranjan090@gmail.com");
		
		driver.findElement(By.xpath("//span[normalize-space()='CONTINUE']")).click();
		
		Thread.sleep(5000);
		WebElement password = driver.findElement(By.xpath("//input[@class='_2IX_2- _3mctLh _17N0em']"));
		password.sendKeys("563245");
		
		
		driver.findElement(By.xpath("//span[normalize-space()='Login']")).click();
		WebElement loggedmobilenumber = driver.findElement(By.xpath("//span[@class='_2WBgv1']"));
		Assert.assertEquals(mobile, loggedmobilenumber);
		System.out.println("user is successfully logged in");
		
		WebElement addaddress = driver.findElement(By.xpath("//img[@class='_2oAAg8']"));
		addaddress.click();

//		Shipping Information:
//			Enter valid shipping information (address, city, state, and zip code).
//			Proceed to the next step.
		Thread.sleep(5000);
		driver.findElement(By.xpath("//p[@class='xCcJAFNbZVTrUex']")).click();
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Ram");
		
		driver.findElement(By.xpath("//input[@name='phone']")).sendKeys("6363066827");
		driver.findElement(By.xpath("//input[@name='pincode']")).sendKeys("800001");
		driver.findElement(By.xpath("//input[@name='addressLine2']")).sendKeys("Indiranagar");
		driver.findElement(By.xpath("//input[@name='addressLine1']")).sendKeys("Indiranagar ,postalpark,road no.5a,patna,bihar, Near shiv parwati community hall, Patna");
		driver.findElement(By.xpath("//input[@name='city']")).sendKeys("Patna");
		Thread.sleep(5000);
		WebElement  State = driver.findElement(By.xpath("//select[@name='state']"));
		
		
		Select s1 = new Select(State);
		s1.selectByVisibleText("Bihar");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[normalize-space()='Save and Deliver Here']")).click();
		
		
		driver.findElement(By.xpath("//button[@class='_2KpZ6l _1seccl _3AWRsL']")).click();
	
	//	Payment Information: Choose a payment method (credit card, for example).
		Thread.sleep(5000);
		WebElement paymentway = driver.findElement(By.xpath("//label[@for='NET_OPTIONS']//div[@class='_1XFPmK']"));
		
		driver.findElement(By.xpath("//label[@for='AXIS']//div[@class='_1XFPmK']")).click();
		
		Thread.sleep(5000);
		Select sc = new Select(driver.findElement(By.xpath("//select[@class='_1kwp-i']")));
		sc.selectByVisibleText("Airtel Payments Bank");
		driver.findElement(By.xpath("//button[text()='PAY ₹396']")).click();
		
		Thread.sleep(5000);
		
	//Review the order
		
		WebElement finalorder = driver.findElement(By.xpath("//div[text()='Bitline Aluminum Alloy Adjustable, Portable, Foldable, Ergonomic, Tablet Laptop Stand_01 Laptop Stand']"));
		
		Assert.assertEquals(cartproduct, finalorder);
		System.out.println("Order summary is verified");
		
	}

}
