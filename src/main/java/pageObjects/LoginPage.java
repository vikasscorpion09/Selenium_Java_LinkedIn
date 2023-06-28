package pageObjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import reusableComponents.ReusableMethods;

public class LoginPage extends ReusableMethods{

	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
		
	
	@FindBy(css = "#username")
	WebElement emailID;
	
	@FindBy(css = "#password")
	WebElement password;
	
	@FindBy(css = "#password-visibility-toggle")
	WebElement show_Password_Button;
	
	@FindBy(linkText = "Forgot password?")
	WebElement forgot_Password_Button;
	
	@FindBy(xpath = "//button[text()='Sign in']")
	WebElement sign_In_Button;
	
	@FindBy(css = "#error-for-username")
	WebElement invalidUsernameMessage;
	
	@FindBy(css = "#error-for-password")
	WebElement invalidPasswordMessage;
	
	@FindBy(linkText = "Forgot password?")
	WebElement forgotPasswordButton;
	
	public void goTo()
	{
		driver.get("https://www.linkedin.com/login");
	}
	
		
	public void enter_Credentials(String testCase){
		waitForWebElementToAppear(emailID);
		String[] testData;
		try {
			testData = fetchDataFromExcel(testCase);
			emailID.sendKeys(testData[0]);
			
			waitForWebElementToAppear(password);
			password.sendKeys(testData[1]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	
	
	public void showPassword() {
		waitForWebElementToAppear(show_Password_Button);
		show_Password_Button.click();
	}
	
	public void clickSignIn() {
		waitForWebElementToAppear(sign_In_Button);
		sign_In_Button.click();
	}	
	
	public String getMessageforInvalidEmail() {
		waitForWebElementToAppear(invalidUsernameMessage);
		return invalidUsernameMessage.getText();
	}
	
	public String getMessageforInvalidPassword() {
		waitForWebElementToAppear(invalidPasswordMessage);
		return invalidPasswordMessage.getText();
	}
	
	public String getForgotPasswordButtonText() {
		waitForWebElementToAppear(forgot_Password_Button);
		return forgot_Password_Button.getText();
	}
	
	public void clickForgotPasswordButton() {
		forgot_Password_Button.click();
	}
}
