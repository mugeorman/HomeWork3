package com.automation.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestCases9to12 {


    private WebDriver driver;
    private By statusCode=By.linkText("Status Codes"); //   xpath: //a[text()='Status Codes'] or //a[contains(text(),'Status Codes')] or //a[@href='/status_codes']
    private  By fiveHundred=By.linkText("500");     //     //a[text()='500']






    @Test
    public void verifyMessageDisplayed500(){

        driver.findElement(statusCode).click();
        driver.findElement(fiveHundred).click();

        WebElement statusMessage= driver.findElement(By.xpath("//p"));   // or tagName("p")
        String actualMessage=statusMessage.getText();
        System.out.println("actualMessage = " + actualMessage);

        Assert.assertTrue(actualMessage.contains("This page returned a 500 status code."));





//        if(actualMessage.contains("This page returned a 500 status code.")){
//            System.out.println("TEST PASSED");
//        }else{
//            System.out.println("TEST FAILED");
//        }

    }


    @DataProvider(name="testData")
    public static Object [] testData(){

        return new Object []{"200","301","404"};
    }


    @Test(dataProvider = "testData")
    public void statusCodes(String code){


        WebElement statusCodeLink = driver.findElement(By.linkText("Status Codes"));
        statusCodeLink.click();

        WebElement statusCode = driver.findElement(By.linkText(code));  // dynamic area we must change it
        statusCode.click();

        String expectedMessage = "This page returned a "+code+" status code";
        WebElement displayedMessageElement = driver.findElement(By.xpath("//p"));
        String actualMessage = displayedMessageElement.getText();


        Assert.assertTrue(actualMessage.contains(expectedMessage),"The status code does not exist");


    }

    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().version("79").setup();
        driver=new ChromeDriver();
        driver.get("https://practice-cybertekschool.herokuapp.com");
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void teardown(){

        driver.quit();
    }

}
