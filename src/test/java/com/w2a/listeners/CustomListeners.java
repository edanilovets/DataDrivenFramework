package com.w2a.listeners;

import com.relevantcodes.extentreports.LogStatus;
import com.w2a.base.TestBase;
import com.w2a.utilities.TestUtil;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.IOException;

public class CustomListeners implements ITestListener {
    public void onTestStart(ITestResult iTestResult) {

    }

    public void onTestSuccess(ITestResult iTestResult) {

        //Write log to file /test-output/html/extent.html
        TestBase.test.log(LogStatus.PASS, iTestResult.getName() + " PASS");
        TestBase.rep.endTest(TestBase.test);
        TestBase.rep.flush();
    }

    public void onTestFailure(ITestResult iTestResult) {

        System.setProperty("org.uncommons.reportng.escape-output","false");

        //Write log to file /test-output/html/index.html
        Reporter.log("Click to see failure screenshot:");
        try {
            TestUtil.captureScreenshot();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + ">Screenshot</a>");
        Reporter.log("<br>");
        Reporter.log("<br>");
        Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + "><img src=" + TestUtil.screenshotName + " height=200 width=200></img></a>");

        //Write log to file /test-output/html/extent.html
        TestBase.test.log(LogStatus.FAIL, iTestResult.getName() + " FAIL");
        TestBase.test.log(LogStatus.FAIL, TestBase.test.addScreenCapture(TestUtil.screenshotName));
        TestBase.rep.endTest(TestBase.test);
        TestBase.rep.flush();
    }

    public void onTestSkipped(ITestResult iTestResult) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {
        TestBase.test = TestBase.rep.startTest(iTestContext.getName());
    }

    public void onFinish(ITestContext iTestContext) {

    }
}
