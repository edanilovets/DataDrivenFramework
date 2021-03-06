package com.w2a.testcases;

import com.w2a.base.TestBase;
import com.w2a.utilities.TestUtil;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;


public class AddCustomerTest extends TestBase {

    @Test(dataProviderClass = TestUtil.class, dataProvider = "excelDataProvider")
    public void addCustomerTest(String firstName, String lastName, String postcode, String alertText){

        click("addCustomerBtn_CSS");
        type("firstname_CSS", firstName);
        type("lastname_XPATH", lastName);
        type("postcode_CSS", postcode);
        click("addbtn_CSS");

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertTrue(alert.getText().contains(alertText));

        alert.accept();
        Assert.fail();

    }
}
