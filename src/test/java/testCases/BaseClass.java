package testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class BaseClass {
    public static WebDriver driver;

    public Logger logger;

    public Properties properties;
    @Parameters({"browser","os"})
    @BeforeClass(groups = {"sanity","regression","master","datadriven"})
    public void setup(String br,String os) throws IOException {
        // this is for Loading config properties file
        FileInputStream file = new FileInputStream("./src/test/resources/config.properties");
        properties = new Properties();
        properties.load(file);


        logger = LogManager.getLogger(this.getClass());

        switch (br.toLowerCase())
        {
            case "chrome" : driver = new ChromeDriver();break;
            case "edge" : driver = new EdgeDriver();break;
            case "firefox" : driver = new FirefoxDriver();break;
            default: System.out.println("Invalid browser ");return;
        }

        //driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(properties.getProperty("url"));
        driver.manage().window().maximize();
    }
    @AfterClass(groups = {"sanity","regression","master","datadriven"})
    public void tearDown(){
        driver.quit();
    }

    public  String randomString()
    {
        return RandomStringUtils.randomAlphabetic(5);
    }

    public  String randomNumber()
    {
        return RandomStringUtils.randomNumeric(10);
    }

    public  String randomAlphaNumber()
    {
        return (RandomStringUtils.randomAlphabetic(3)+"@"+RandomStringUtils.randomNumeric(3));
    }
    //screenshot
    public String captureScreen(String tname) throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
        File targetFile=new File(targetFilePath);

        sourceFile.renameTo(targetFile);

        return targetFilePath;

    }
}
