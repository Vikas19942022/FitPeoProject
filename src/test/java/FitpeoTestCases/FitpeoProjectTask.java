package FitpeoTestCases;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class FitpeoProjectTask {
   WebDriver driver;
   @BeforeMethod
   public void setup() {
       // Navigate to the FitPeo Homepage
       driver = new ChromeDriver();
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
       driver.manage().window().maximize();
       driver.get("https://www.fitpeo.com/");
       driver.findElement(By.xpath("//a[@href='/revenue-calculator']")).click();
   }
  
   @Test(priority = 1)
   public void navigateToRevenueCalculatorPage() throws InterruptedException {
       // Locate the element and click it
	   driver.findElement(By.xpath("//div[text()='Revenue Calculator']")).click();

       // Wait for the page heading to be visible
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       WebElement verify_Revenue_Calculator_Page = wait.until(
               ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='MuiTypography-root MuiTypography-body1 inter css-1fefu7m']"))
       );
       
       // Get the text and verify it
       String text = verify_Revenue_Calculator_Page.getText();
       String expectedHeading = "RPM and CCM Programs - CPT code reimbursement (Non-Facility Rates) NOTE: These Numbers Vary State to State"; // Update this as per actual heading
       
       // Print the actual text to debug if the assertion fails
       System.out.println("Actual heading text: " + text);

       // Assert that the text matches the expected heading
       Assert.assertEquals(text, expectedHeading, "Revenue Calculator Page does not match!");
       Thread.sleep(2000); // Optional, for extra wait time if needed
   }


  
   @Test(priority = 2)
   public void scrollDownToSliderSection() throws InterruptedException {
       // Scroll down the page until the revenue calculator slider is visible
       WebElement sliderSection = driver.findElement(By.xpath("//h4[text()='Medicare Eligible Patients']"));
       JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
       jsExecutor.executeScript("arguments[0].scrollIntoView(true);", sliderSection);
      
       // Adding a slight delay for smooth scrolling and loading
       Thread.sleep(2000);
   }
   @Test(priority = 3)
   public void slideto820() throws InterruptedException {
       // Scroll to the slider section
   	WebElement sliderBtn = driver.findElement(By.xpath("//h4[text()='Medicare Eligible Patients']"));
       JavascriptExecutor jse = (JavascriptExecutor) driver;
       jse.executeScript("arguments[0].scrollIntoView(true);", sliderBtn);
      
       // Locate slider and the input/output element
       Thread.sleep(2000); // Wait for the page to load
      
       WebElement slider = driver.findElement(By.xpath("//span[@data-index='0' and contains(@class, 'MuiSlider-thumb')]"));
       WebElement output = driver.findElement(By.xpath("//input[@type='number' and contains(@class, 'MuiInputBase-input')]"));
      
       // Perform the drag-and-drop action on the slider (drag to approximately desired position)
       Actions action = new Actions(driver);
       action.dragAndDropBy(slider, 93, 0).perform();
      
       // Wait for the value to update
       Thread.sleep(2000);
      
       // Fine-tune the value by sending the RIGHT arrow key 4 times
       for (int i = 0; i < 4; i++) {
           action.sendKeys(Keys.ARROW_RIGHT).perform();  // Increment the value by 1 unit
           Thread.sleep(100);  // Wait for the value to update
       }
      
       // After the loop, get the final value from the slider
       String finalSliderValue = output.getAttribute("value");
      
       // Print only the final slider value
       System.out.println("Final Slider Value: " + finalSliderValue);
      
       // Assert that the final value matches the expected value (820)
       Assert.assertEquals(finalSliderValue, "820", "Slider value is incorrect! Expected: 820, Found: " + finalSliderValue);
      
   }
   @Test(priority = 4)
   public void updateTextbox560() {
       WebElement sliderBtn = driver.findElement(By.xpath("//h4[text()='Medicare Eligible Patients']"));
       JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
       jsExecutor.executeScript("arguments[0].scrollIntoView(true);", sliderBtn);
       // Click on the text field associated with the slider
       WebElement textBox = driver.findElement(By.xpath("//div//input[@type='number']"));
       textBox.click();
       // Clear existing value and enter "560"
       textBox.sendKeys(Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, "560");
       // Wait for the slider to update
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       wait.until(ExpectedConditions.attributeToBe(textBox, "value", "560"));
       // Verify if the slider value updates to 560
       String updatedValue = textBox.getAttribute("value");
       Assert.assertEquals(updatedValue, "560", "Text field value not updated correctly.");
       System.out.println("Validation successful: Text field value updated to 560");
   }
   @Test(priority = 5)
   public void validateSliderPositionFor560() {
       WebElement sliderSection = driver.findElement(By.xpath("//h4[text()='Medicare Eligible Patients']"));
       JavascriptExecutor jse = (JavascriptExecutor) driver;
       jse.executeScript("arguments[0].scrollIntoView(true);", sliderSection);
       // Locate the text field for the slider
       WebElement textField = driver.findElement(By.xpath("//div//input[@type='number']"));
       textField.click();
       textField.sendKeys(Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, "560");
       // Wait for the slider to adjust its position
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       wait.until(ExpectedConditions.attributeToBe(textField, "value", "560"));
       // Locate the slider thumb and check its position
       WebElement slider = driver.findElement(By.xpath("//span[contains(@class, 'MuiSlider-thumb')]"));
       String sliderStyle = slider.getAttribute("style");
       // Assuming you know the exact expected style for the value of 560
       String expectedLeftPosition = "left: 28%"; // Adjust based on your observation
       Assert.assertTrue(sliderStyle.contains(expectedLeftPosition), "Slider position is incorrect for value 560!");
       System.out.println("Slider position validated successfully: " + sliderStyle);
   }
   @Test(priority = 6)
   public void Select_CPT_Codes() {
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       WebElement cptCodesHeader = wait.until(
               ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='MuiBox-root css-1eynrej']")));
       // Scroll to the 'CPT Codes' section
       JavascriptExecutor js = (JavascriptExecutor) driver;
       js.executeScript("arguments[0].scrollIntoView(true);", cptCodesHeader);
       // Wait for the checkboxes to be visible
       WebElement checkbox1 = driver.findElement(By.xpath("(//input[@type='checkbox'])[1]"));
       WebElement checkbox2 = driver.findElement(By.xpath("(//input[@type='checkbox'])[2]"));
       WebElement checkbox3 = driver.findElement(By.xpath("(//input[@type='checkbox'])[3]"));
       WebElement checkbox4 = driver.findElement(By.xpath("(//input[@type='checkbox'])[8]"));
       // Click the checkboxes for CPT-99091, CPT-99453, CPT-99454, and CPT-99474
       checkbox1.click();
       checkbox2.click();
       checkbox3.click();
       checkbox4.click();
   }
   @Test(priority = 7)
   public void Validate_Total_Recurring_Reimbursement() throws InterruptedException {
   	WebElement slider = driver.findElement(By.xpath("//span[@data-index='0' and contains(@class, 'MuiSlider-thumb')]"));
       Actions action = new Actions(driver);
       action.dragAndDropBy(slider, 93, 0).perform(); // Move slider to approximately desired position
       Thread.sleep(2000); // Wait for slider movement to reflect in the UI
      
       // Fine-tune slider using arrow keys
       for (int i = 0; i < 4; i++) {
           action.sendKeys(Keys.ARROW_RIGHT).perform();
           Thread.sleep(100); // Wait for updates
       }
      
       // Wait for the 'CPT Codes' section to appear and scroll to it
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       WebElement cptCodesHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='MuiBox-root css-1eynrej']")));
      
       JavascriptExecutor js = (JavascriptExecutor) driver;
       js.executeScript("arguments[0].scrollIntoView(true);", cptCodesHeader);
      
       // Select checkboxes for CPT-99091, CPT-99453, CPT-99454, and CPT-99474
       WebElement checkbox1 = driver.findElement(By.xpath("(//input[@type='checkbox'])[1]"));
       WebElement checkbox2 = driver.findElement(By.xpath("(//input[@type='checkbox'])[2]"));
       WebElement checkbox3 = driver.findElement(By.xpath("(//input[@type='checkbox'])[3]"));
       WebElement checkbox4 = driver.findElement(By.xpath("(//input[@type='checkbox'])[8]"));
      
       checkbox1.click();
       Thread.sleep(1000);
       checkbox2.click();
       Thread.sleep(1000);
       checkbox3.click();
       Thread.sleep(1000);
       checkbox4.click();
       Thread.sleep(2000); // Wait for selections to register
      
       // Validate Total Recurring Reimbursement
       WebElement reimbursementHeader = driver.findElement(By.xpath("(//p[@class='MuiTypography-root MuiTypography-body1 inter css-1bl0tdj'])[4]"));
       String headerText = reimbursementHeader.getText();
       Assert.assertTrue(headerText.contains("$110700"), "Validation Failed: Expected $110700 but found " + headerText);
       System.out.println("Validation successful, header displays: " + headerText);
      
   }
   @AfterMethod
   public void tearDown() {
       driver.quit();
   }
}

