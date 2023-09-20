package basePackage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.time.Duration;

public class Base {
    public static AppiumDriverLocalService service;
    public static WebDriverWait waitDriver;
    public static AppiumDriver driver;

    //start Appium server by script
    @BeforeTest
    public void startAppiumServer() throws IOException, InterruptedException {
        checkIfServerIsRunnning(4723);
        service = new AppiumServiceBuilder().withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js")).withIPAddress("127.0.0.1").usingPort(4723).withArgument(() -> "--use-plugins", "images").build();
        service.start();
        launchApp("Androidn");


    }

        //@Parameters({"platform"})
        public static void launchApp(String platform) throws IOException, InterruptedException {
        DesiredCapabilities dc = new DesiredCapabilities();

        if(platform.equalsIgnoreCase("iOS")) {
            dc.setCapability("automationName", "XCUI_TEST");
            dc.setCapability("platformName", "iOS");
            dc.setCapability("platformVersion", "16.2");
            dc.setCapability("deviceName", "iPhone 14");
            dc.setCapability("udid", "CD569630-64BD-4F59-8C10-2AA3356B71C9");
            dc.setCapability("appium:plugins", "execute-driver");
            dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
            //dc.setCapability("bundleId", "com.mproov.app.d");
            dc.setCapability("app", "/Users/karishmabarot/Downloads/TestApp.app");
            driver = new AppiumDriver(service.getUrl(), dc);
            System.out.println("App launched by driver!!!");
        }
        else if(platform.equalsIgnoreCase("Android")){
            try {
                ProcessBuilder processBuilder = new ProcessBuilder("emulator", "-avd", "Pixel_3a_API_33_x86_64");
                Process emulatorProcess = processBuilder.start();
                int exitCode = emulatorProcess.waitFor();
                System.out.println("Emulator exited with code: " + exitCode);
            }
            catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }


            dc.setCapability("automationName", "UiAutomator2");
            dc.setCapability("platformName", "Android");
            //dc.setCapability("platformVersion", "16.2");
            dc.setCapability("deviceName", "emulator-5554");
            dc.setCapability("appPackage", "com.mproov.d");
            dc.setCapability("appActivity", "com.mproov.MainActivity");
            dc.setCapability("app", "/Users/karishmabarot/Downloads/app-dev-release (1).apk");
            driver = new AppiumDriver(service.getUrl(), dc);
            System.out.println("App launched by driver!!!");
        }
        else{
            System.out.println("Please provide device to run test!");
        }
    }

    //check if Appium server is already running
    public static boolean checkIfServerIsRunnning(int port) {

        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.close();
        } catch (IOException e) {
            isServerRunning = true;
        } finally {
            serverSocket = null;
        }
        return isServerRunning;
    }

    public static void explicitClick(WebElement webElement) {
        waitDriver = new WebDriverWait(driver, Duration.ofSeconds(30));
        waitDriver.until(ExpectedConditions.visibilityOf(webElement)).click();
    }

    //stop Appium server by script
    @AfterTest
    public void tearDown() {
        driver.quit();
        service.stop();
    }
}
