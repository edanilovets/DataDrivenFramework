package com.w2a.utilities;

import com.w2a.base.TestBase;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.sql.Time;

public class TestUtil extends TestBase {

    public static String screenshotName = "error.jpg";
    public static void captureScreenshot(){
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir") + "\\test-output\\html\\screenshots\\" + screenshotName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
