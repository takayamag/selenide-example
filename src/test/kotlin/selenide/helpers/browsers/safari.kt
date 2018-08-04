package selenide.helpers.browsers

import com.codeborne.selenide.WebDriverProvider
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.safari.SafariDriver
import org.openqa.selenium.safari.SafariDriverService
import org.openqa.selenium.safari.SafariOptions
import selenide.helpers.BrowserUtils

object Safari {
    class CustomWebDriverProvider : WebDriverProvider {
        override fun createDriver(desiredCapabilities: DesiredCapabilities?): WebDriver {
            val service = SafariDriverService.Builder()
                    // .usingDriverExecutable(File(BrowserUtils.getDriverLocation(BrowserType.SAFARI)))
                    .usingAnyFreePort()
                    .build()

            val options = SafariOptions()
            // プロキシを設定する
            if (BrowserUtils.USE_QA_PROXY) {
                options.setProxy(BrowserUtils.getProxy())
            }

            options.useTechnologyPreview = true

            return SafariDriver(service, options)
        }
    }
}