package reusableComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReusableMethods {

	WebDriver driver;

	public ReusableMethods(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}



	public void waitForWebElementToAppear(WebElement findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));

	}

	public String[] fetchDataFromExcel(String testCase) throws IOException {

		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//resources//User_Data.xlsx");
		XSSFWorkbook workBook = new XSSFWorkbook(fis);
		String[] credentials = new String[2];
		if (testCase.equals("Empty Credentials")) {
			credentials[0] = "";
			credentials[1] = "";
		} else {
			XSSFSheet sheet1 = workBook.getSheet("Sheet1");
			for (int i = 0; i < sheet1.getLastRowNum(); i++) {
				if (sheet1.getRow(i).getCell(0).getStringCellValue().equalsIgnoreCase(testCase)) {
					credentials[0] = sheet1.getRow(i).getCell(1).getStringCellValue();
					credentials[1] = sheet1.getRow(i).getCell(2).getStringCellValue();
					workBook.close();
					fis.close();
					break;
				}

			}
		}

		return credentials;

	}

}
