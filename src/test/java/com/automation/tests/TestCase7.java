package com.automation.tests;

import com.automation.utilities.BrowserUtilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCase7 {


    private WebDriver driver;
    private By fileUpload=By.linkText("File Upload"); // xpath: //a[@href='/upload']
    private By uploadButton=By.id("file-submit");
    private By chooseFileButton=By.id("file-upload");
    private By fileUploadedMessage=By.xpath("//h3[text()='File Uploaded!']");
    private By uploadedFileName=By.id("uploaded-files");


    @Test
    public void verifyFileUploadedAndFileNameDisplayed(){

        driver.findElement(fileUpload).click();


        BrowserUtilities.wait(3);

       // String filePath=System.getProperty("\"C:\\Users\\memet\\Desktop\\notesAboutSelenium.txt\"");
        driver.findElement(chooseFileButton).sendKeys("C://Users//memet//Desktop//notesAboutSelenium.txt");

        BrowserUtilities.wait(4);

        driver.findElement(uploadButton).click();

        BrowserUtilities.wait(3);

        String subjectExpected="File Uploaded!";
        String actual=driver.findElement(fileUploadedMessage).getText();

        Assert.assertEquals(actual,subjectExpected);

        Assert.assertTrue(driver.findElement(uploadedFileName).isDisplayed());








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
