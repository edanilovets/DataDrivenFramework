package com.w2a.testcases;

import com.w2a.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BankManagerLoginTest extends TestBase {

    @Test
    public void loginAsBankManager(){
        driver.findElement(By.cssSelector(OR.getProperty("bmlBtn"))).click();

        //check is Customer button is loaded
        Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustomerBtn"))),"Login not successful!");
        Assert.fail("Login as bank manager was not successful");
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
