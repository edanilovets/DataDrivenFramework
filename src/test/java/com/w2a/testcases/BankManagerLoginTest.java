package com.w2a.testcases;

import com.w2a.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BankManagerLoginTest extends TestBase {

    @Test
    public void bankManagerLoginTest(){

        //Login is done by click on one button only
        click("bmlBtn_CSS");

        //Check is Customer button is loaded
        Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustomerBtn_CSS"))),"Login not successful!");

    }
    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
