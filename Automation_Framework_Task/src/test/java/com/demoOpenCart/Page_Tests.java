package com.demoOpenCart;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import com.demofeatures.HomePage;
import com.demofeatures.LoginPage;
import com.demofeatures.PredefineData;
import com.demofeatures.UserFeatures;



public class Page_Tests {
		WebDriver driver=new ChromeDriver();
	
	
	HomePage homePage = new HomePage(driver);
	LoginPage loginPage = new LoginPage(driver);
	
	UserFeatures userfeaturePage = new UserFeatures(driver);
	static PredefineData testdata;
	

	String Productquantity=RandomStringUtils.random(1, false, true);
	
	@BeforeTest
	public void before_test() throws IOException {
		testdata =new PredefineData();
		driver.get(testdata.property.getProperty("Baseurl"));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
	}
	@Test(priority=1)
	public void login_Positive() throws Throwable {
		homePage.Click_login_from_MyAccount();
		loginPage.provideEmail(testdata.property.getProperty("Email"));
		loginPage.providePassword(testdata.property.getProperty("password"));
		loginPage.ClickSubmitButton();
		Assert.assertEquals(loginPage.verifySuccessLogin(), "Account");
		loginPage.ClickLogoutButton();
		TakesScreenshot scrShot =((TakesScreenshot)driver);

        //Call getScreenshotAs method to create image file

                File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

            //Move image file to new destination

                File DestFile=new File("C:\\Users\\sys\\Pictures\\DemoOpenCart.Com-Automated-master\\Screenshot");

			
	}


	@Test(priority=2)
	public void Add_To_Wishlist() {
		homePage.Click_login_from_MyAccount();
		loginPage.provideEmail(testdata.property.getProperty("Email"));
		loginPage.providePassword(testdata.property.getProperty("password"));
		loginPage.ClickSubmitButton();
		Assert.assertEquals(loginPage.verifySuccessLogin(), "Account");
		userfeaturePage.ClickHomePageButton();
		userfeaturePage.ClickIphoneProductViewButton();
		userfeaturePage.ClickWishlistAddButton();
		Assert.assertEquals(userfeaturePage.VerifyWishlistAddMessage(), "wish list");
		userfeaturePage.ClickHomePageButton();
		loginPage.ClickLogoutButton();
	}
	@Test(priority=3)
	public void Add_To_Cart_From_Productview() {
		userfeaturePage.ClickIphoneProductViewButton();
		userfeaturePage.ClickAddToCartButtonFromProductview();
		Assert.assertEquals(userfeaturePage.VerifyShoppingcartAddMessage(), "shopping cart");
		userfeaturePage.ClickHomePageButton();
	}
	@Test(priority=4)
	public void Add_To_Cart_From_Wishlist() {
		homePage.Click_login_from_MyAccount();
		loginPage.provideEmail(testdata.property.getProperty("Email"));
		loginPage.providePassword(testdata.property.getProperty("password"));
		loginPage.ClickSubmitButton();
		Assert.assertEquals(loginPage.verifySuccessLogin(), "Account");
		userfeaturePage.ClickWishlistViewButton();
		userfeaturePage.ClickAddToCartButtonFromWishlist();
		Assert.assertEquals(userfeaturePage.VerifyShoppingcartAddMessage(), "shopping cart");
		loginPage.ClickLogoutButton();
	}
		
	@AfterTest
	public void after_test() {
		driver.close();
	}
}
