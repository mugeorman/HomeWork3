package com.automation.tests;

import com.automation.utilities.BrowserUtilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TestCase1to5 {

     private WebDriver driver;
     private By registrationForm=By.linkText("Registration Form");
     private By dateOfBirth=By.name("birthday");
     private By dobNotValidMessage=By.cssSelector("small[data-bv-result='INVALID']");
     private By cPlusPlus=By.xpath("//input[@id='inlineCheckbox1' and @value='cplusplus']"); //  //input[@id='inlineCheckbox1' and @value='cplusplus']/following-sibling::label  or //input[ @value='cplusplus']/following-sibling::label
     private By java=By.xpath("//input[@id='inlineCheckbox2']/following-sibling::label");
     private By javaScript=By.xpath("//input[@id='inlineCheckbox3']/following-sibling::label");
     private By firstName=By.name("firstname");
     private By firstNameWarningMessage=By.xpath("//small[@data-bv-result='INVALID' and text()='first name must be more than 2 and less than 64 characters long']");
     private By lastName=By.name("lastname");
     private By lastNameWarningMessage=By.xpath("//small[@data-bv-for='lastname' and text()='The last name must be more than 2 and less than 64 characters long']");
     private By userNameBy= By.name("username"); // css: [name='username']  xpath: //input[@name='username']
     private By emailBy= By.name("email");
     private By password=By.name("password");
     private By phoneNumberBy=By.cssSelector("[name='phone']");
     private By maleBy=By.cssSelector(".radio:nth-child(1) input"); // xpath //input[@value='male']
     private By femaleBy=By.cssSelector(".radio:nth-child(2) input"); // xpath //input[@value='female']
     private By otherBy=By.cssSelector(".radio:nth-child(3) input");
     private By dobBy=By.cssSelector("[name='birthday']");
     private By department = By.xpath("//select[@name='department']");
     private By jobTitleBy= By.xpath("//select[@name='job_title']");
     private By cPlusPlusBy=By.xpath("//input[@value='cplusplus']");
     private By javaBy=By.xpath("//input[@value='java']");
     private By javaScriptBy= By.cssSelector("[id='inlineCheckbox3']");
     private By signUpBy=By.cssSelector("[id='wooden_spoon']");
     private By registrationCompleteMessageBy=By.xpath(" //h4/following-sibling::p");





     @Test(description = "Verify that warning message displays when user enters invalid data in date of birth box")
     public void verifyWarningMessageDisplayed(){


         driver.findElement(dateOfBirth).sendKeys("wrong_dob");

        WebElement notValidMessg=driver.findElement(dobNotValidMessage);
         //String expected="The date of birth is not valid";

         assertTrue(notValidMessg.isDisplayed());

     }

     @Test(description = "Verify that programming languages c++, java, JavaScript options are displayed")
     public void verifyLanguagesDisplayed(){

         assertTrue(driver.findElement(cPlusPlus).isDisplayed());
         assertTrue(driver.findElement(java).isDisplayed());
         assertTrue(driver.findElement(javaScript).isDisplayed());


     }

     @Test(description = "Verify that warning message displays when user enters less than 2 characters into first name input box")
     public void verifyFirstNameWarningDisplayed(){

         driver.findElement(firstName).sendKeys("m");

         assertTrue(driver.findElement(firstNameWarningMessage).isDisplayed());

     }

     @Test(description = "Verify that warning message displays when user enters less than 2 characters into last name input box")
     public void verifyLastNameWarningDisplayed(){

         driver.findElement(lastName).sendKeys("a");
         assertTrue(driver.findElement(lastNameWarningMessage).isDisplayed());
     }

     @Test(description = "completing registration form and display successfully completed message")
     public void verifyRegistrationMessage(){

         driver.findElement(firstName).sendKeys("Metin");
         driver.findElement(lastName).sendKeys("Kazmirci");
         driver.findElement(userNameBy).sendKeys("metinkazmirci");
         driver.findElement(emailBy).sendKeys("metink@mynet.com");
         driver.findElement(password).sendKeys("147258369");
         driver.findElement(phoneNumberBy).sendKeys("571-898-6936");
         driver.findElement(maleBy).click();
         driver.findElement(dobBy).sendKeys("09/06/1949");

         Select departmentSelect=new Select(driver.findElement(department));
         departmentSelect.selectByVisibleText("Department of Engineering");

         Select jobTitleSelect=new Select(driver.findElement(jobTitleBy));
         jobTitleSelect.selectByVisibleText("Manager");

         driver.findElement(javaBy).click();

         driver.findElement(signUpBy).click();

         assertTrue(driver.findElement(registrationCompleteMessageBy).isDisplayed());



     }




     @BeforeMethod
    public void setUp(){

         WebDriverManager.chromedriver().version("79").setup();
         driver = new ChromeDriver();
         driver.get("https://practice-cybertekschool.herokuapp.com/");
         driver.manage().window().maximize();
         BrowserUtilities.wait(3);
         driver.findElement(registrationForm).click();

     }

     @AfterMethod
    public void tearDown(){

         driver.quit();

     }
}
