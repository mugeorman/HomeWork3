package com.automation.tests;

import com.automation.utilities.BrowserUtilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCase6 {


    private WebDriver driver;


    @Test
    public void verifyMessageDisplayed(){
        driver.get("https://www.tempmailaddress.com/");
        driver.manage().window().maximize();

        WebElement email=driver.findElement(By.id("email"));
        String emailText= email.getText().trim();

        driver.navigate().to("https://practice-cybertekschool.herokuapp.com/");

        WebElement signUpForMailing=driver.findElement(By.linkText("Sign Up For Mailing List"));
        signUpForMailing.click();

        WebElement fullName=driver.findElement(By.cssSelector("[name='full_name']"));
        fullName.sendKeys("Muge Orman");

        WebElement emailSignUpPage=driver.findElement(By.cssSelector("[name='email']"));
        emailSignUpPage.sendKeys(emailText);

        WebElement signUpPage= driver.findElement(By.cssSelector("[name='wooden_spoon']"));
        signUpPage.click();

        WebElement signUpMessage=driver.findElement(By.cssSelector("[name='signup_message']"));

        Assert.assertTrue(signUpMessage.isDisplayed());



        driver.navigate().to("https://www.tempmailaddress.com/");

        BrowserUtilities.wait(2);

        WebElement receivedEmail=driver.findElement(By.cssSelector(".from"));
        String emailReceived =receivedEmail.getText().trim();
       // System.out.println("emailReceived = " + emailReceived);

        String expected="do-not-reply@practice.cybertekschool.com";

        Assert.assertEquals(emailReceived,expected);

        receivedEmail.click();

        WebElement from=driver.findElement(By.cssSelector("[id='odesilatel']"));
        String actual=from.getText().trim();

        Assert.assertEquals(actual,expected);

        WebElement subject=driver.findElement(By.cssSelector("[id='predmet']"));
        String subjectActual=subject.getText().trim();
        String expectedSubject="Thanks for subscribing to practice.cybertekschool.com!";

        Assert.assertEquals(subjectActual,expectedSubject);






    }

    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().version("79").setup();
        driver= new ChromeDriver();



    }

    @AfterMethod
    public void teardown(){
         driver.quit();
    }



}
