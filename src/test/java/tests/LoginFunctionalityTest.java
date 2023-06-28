package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import testComponents.BaseTest;

public class LoginFunctionalityTest extends BaseTest{

	// Verify a valid user should be able to login successfully
	@Test(enabled = true)
	public void test_Successful_Login_And_Logout() {
		//Fetching data from GlobalData.properties file
		loginPage.enter_Credentials("Successful Login");
		loginPage.showPassword();
		loginPage.clickSignIn();
		
		Assert.assertEquals(driver.getCurrentUrl(), prop.getProperty("linkedinHomePage"));
	}
	
	//Verify that a user with invalid username should get a validation message displayed on the screen.
	@Test(enabled = true)
	public void test_Invalid_Username() {
		
		loginPage.enter_Credentials("Invalid Username");
		loginPage.showPassword();
		loginPage.clickSignIn();
		
		Assert.assertEquals(loginPage.getMessageforInvalidEmail(), prop.getProperty("invalidUsernameMessage"));
		
		
	}
	
	//Verify that a user with invalid password should get a validation message displayed on the screen.
	@Test(enabled = true)
	public void test_Invalid_Password() {
		
		loginPage.enter_Credentials("Invalid Password");
		loginPage.showPassword();
		loginPage.clickSignIn();
		
		Assert.assertEquals(loginPage.getMessageforInvalidPassword(), prop.getProperty("invalidPasswordMessage"));
	}
	
	
	//Verify that a user with no username and password should get a validation message displayed on the screen.
	@Test(enabled = true)
	public void test_Empty_Credentials() {
		loginPage.enter_Credentials("Empty Credentials");
		loginPage.clickSignIn();
		
		Assert.assertEquals(loginPage.getMessageforInvalidEmail(), prop.getProperty("noCredentialsMessage"));
	}
	
	//Failing this test case intentionally
	@Test(enabled = true)
	public void test_Failing_Intentionally() {
		
		Assert.assertFalse(true);
	}
	
	//Verify that Forgot Password link is displayed on the screen
	@Test(enabled = true)
	public void test_Forgot_Password_Displayed_And_Click() {
		Assert.assertEquals(loginPage.getForgotPasswordButtonText(), prop.getProperty("forgotPasswordButton"));
		loginPage.clickForgotPasswordButton();
		
		Assert.assertEquals(driver.getCurrentUrl(),prop.getProperty("forgotPasswordURL"));
	}

}
