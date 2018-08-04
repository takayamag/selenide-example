package selenide.helpers.browsers

import com.codeborne.selenide.WebDriverProvider
import org.openqa.selenium.PageLoadStrategy
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeDriverService
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import selenide.helpers.BrowserType
import selenide.helpers.BrowserUtils
import java.io.File
import java.util.HashMap

object ChromeSp {
    class CustomWebDriverProvider : WebDriverProvider {
        override fun createDriver(desiredCapabilities: DesiredCapabilities?): WebDriver {
            val service = ChromeDriverService.Builder()
                    .usingDriverExecutable(File(BrowserUtils.getDriverLocation(BrowserType.CHROME)))
                    .usingAnyFreePort()
                    .build()

            val options = ChromeOptions()
            // options.setPageLoadStrategy(PageLoadStrategy.NORMAL)
            options.setHeadless(BrowserUtils.USE_HEADLESS)

            // プロキシを設定する
            if (BrowserUtils.USE_QA_PROXY) {
                options.setProxy(BrowserUtils.getProxy())
            }

            // ChromeのSPシミュレータを使用する
            val mobileEmulation = HashMap<String, String>()
            mobileEmulation["deviceName"] = BrowserUtils.TEST_DEVICE_NAME
            options.setExperimentalOption("mobileEmulation", mobileEmulation)

            return if (BrowserUtils.USE_GRID_HUB) {
                RemoteWebDriver(BrowserUtils.GRID_HUB_URL, options)
            } else {
                options.setBinary(BrowserUtils.CHROME_BINARY)
                ChromeDriver(service, options)
            }
        }
    }
}