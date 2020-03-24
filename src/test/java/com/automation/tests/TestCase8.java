package com.automation.tests;

import com.automation.utilities.BrowserUtilities;
import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCase8 {

           private WebDriver driver;



      @Test
      public void verifyMessageDisplayed(){

          driver.findElement(By.linkText("Autocomplete")).click();
          driver.findElement(By.id("myCountry")).sendKeys("United States of America");
          driver.findElement(By.xpath("//input[@type='button']")).click();

          BrowserUtilities.wait(3);

           String expected="You selected: United States of America";
           String actual= driver.findElement(By.xpath("//p[@id='result']")).getText();


          Assert.assertEquals(actual,expected);

      }







      @BeforeTest
      public void setup(){

          WebDriverManager.chromedriver().version("79").setup();
          driver=new ChromeDriver();
          driver.get("https://practice-cybertekschool.herokuapp.com/");
          driver.manage().window().maximize();

          BrowserUtilities.wait(2);

      }


      @AfterMethod
     public void teardown(){

          driver.quit();


      }
}
