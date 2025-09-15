package TestExercise;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assignment {
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().window().maximize();

			driver.get("https://automationexercise.com/");

			WebElement product = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("/html/body/header/div/div/div/div[2]/div/ul/li[2]/a")));

			product.click();
			System.out.println("Product button Clicked");

			WebElement SearchProduct = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/section[1]/div/input")));

			SearchProduct.sendKeys("Shirts");

			System.out.println("Input Shirt ");

			WebElement SearchNow = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/section[1]/div/button")));
			SearchNow.click();

			System.out.println("Product Search ");

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,400)");

			WebElement Price = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("/html/body/section[2]/div/div/div[2]/div/div[2]/div/div[1]/div[1]/h2")));

			String ShirtPrice = Price.getText();
			System.out.println(ShirtPrice);

			WebElement productName = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("/html/body/section[2]/div/div/div[2]/div/div[2]/div/div[1]/div[1]/p")));

			String shirtname = productName.getText();
			System.out.println(shirtname);

			WebElement AddtoCart = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("/html/body/section[2]/div/div/div[2]/div/div[2]/div/div[1]/div[1]/a")));
			AddtoCart.click();
			System.out.println("Add to cart product");

			WebElement ViewCart = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("/html/body/section[2]/div/div/div[2]/div/div[1]/div/div/div[2]/p[2]/a/u")));
			ViewCart.click();
			System.out.println("View cart");

			WebElement CartDetailsPrice = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("/html/body/section/div/div[2]/table/tbody/tr/td[5]/p")));
			String Cartprice = CartDetailsPrice.getText();
			System.out.println(Cartprice);

			String shirtPriceText = ShirtPrice.replaceAll("[^0-9]", "");
			String cartPriceText = Cartprice.replaceAll("[^0-9]", "");

			if (shirtPriceText.equals(cartPriceText)) {
				System.out.println("Both prices are equal: " + shirtPriceText);
			} else {
				System.out.println(" Prices are not equal. Shirt: " + shirtPriceText + " | Cart: " + cartPriceText);
			}

			WebElement ShirtTypeCart = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("/html/body/section/div/div[2]/table/tbody/tr/td[2]/h4/a")));
			String ShirtnameCart = ShirtTypeCart.getText();

			if (shirtname.equals(ShirtnameCart)) {
				System.out.println(" Both product is same: " + shirtname);
			} else {
				System.out.println(" Both product is not same Selected item and card item  " + shirtname + " | Cart: "
						+ ShirtnameCart);
			}

		} catch (Exception e) {
			System.out.println("An error occurred during automation: " + e.getMessage());
			e.printStackTrace();
		} finally {
//			// close browser after test
//			driver.quit();
		}
	}
}

