package selenide.helpers

import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import org.openqa.selenium.remote.DesiredCapabilities
import io.appium.java_client.remote.MobileCapabilityType
import org.openqa.selenium.WebElement
import java.net.URL
import java.util.concurrent.TimeUnit

enum class AppiumDriverType {
    ANDROID_CHROME,
    IOS_SAFARI
}

object AppiumUtils {

    fun launchIOSDriver(): IOSDriver<WebElement> {
        val capabilities = iOSCapabilities()
        val driver = IOSDriver<WebElement>(URL("http://127.0.0.1:4723/wd/hub"), capabilities)
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS)

        return driver
    }

    fun closeIOSDriver(driver: IOSDriver<WebElement>?) {
        driver?.closeApp()
    }

    fun launchAndroidDriver(): AndroidDriver<WebElement> {
        val capabilities = androidCapabilities()
        val driver = AndroidDriver<WebElement>(URL("http://127.0.0.1:4723/wd/hub"), capabilities)
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS)

        return driver
    }

    fun closeAndroidDriver(driver: AndroidDriver<WebElement>?) {
        driver?.closeApp()
    }

    // https://appium.github.io/java-client/io/appium/java_client/remote/AndroidMobileCapabilityType.html

    // show devices list of xcode: xcrun simctl list
    private fun iOSCapabilities(): DesiredCapabilities {
        // val app = File("")

        val caps = DesiredCapabilities.iphone()
        caps.setCapability(MobileCapabilityType.APPIUM_VERSION, "1.7.1")
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS")
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0")
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 7")
        caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari")
        caps.setCapability(MobileCapabilityType.LANGUAGE, "ja")
        caps.setCapability(MobileCapabilityType.LOCALE, "ja-JP")
        caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "30")
        // caps.setCapability(MobileCapabilityType.UDID, "<udid>")
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest")
        caps.setCapability("unicodeKeyboard", true)  // android only?
        caps.setCapability("resetKeyboard", true)  // android only?

        return caps
    }

    private fun androidCapabilities(): DesiredCapabilities {
        // val app = File("")

        val caps = DesiredCapabilities.android()
        caps.setCapability(MobileCapabilityType.APPIUM_VERSION, "1.7.1")
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android")
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.0")
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator")
        caps.setCapability("avd", "AVD_Nexus_5X_API_24_Nougat")
        caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome")
        //caps.setCapability(MobileCapabilityType.LANGUAGE, "ja")  // simulator / emulator only
        //caps.setCapability(MobileCapabilityType.LOCALE, "ja-JP")  // simuklator / emulator only
        caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60")

        caps.setCapability("unicodeKeyboard", true)
        caps.setCapability("resetKeyboard", true)
        caps.setCapability(MobileCapabilityType.APP, "com.android.chrome")
        caps.setCapability(MobileCapabilityType.ORIENTATION, "PORTRAIT")

        caps.setCapability("appWaitPackage", "com.android.chrome")
        caps.setCapability("deviceReadyTimeout", "120")
        caps.setCapability("androidDeviceReadyTimeout", "120")
        caps.setCapability("avdLaunchTimeout", "120000") // 2分
        caps.setCapability("avdReadyTimeout", "240000")
        // caps.setCapability("chromedriverExecutable", "/usr/local/bin/chromedriver")

        caps.setCapability("fastReset", true)
        caps.setCapability("appActivity", "com.android.chrome/com.google.android.apps.chrome.Main")

        return caps
    }
}