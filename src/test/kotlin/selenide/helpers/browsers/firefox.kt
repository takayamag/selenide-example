package selenide.helpers.browsers

import com.codeborne.selenide.WebDriverProvider
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.firefox.FirefoxProfile
import org.openqa.selenium.firefox.GeckoDriverService
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import selenide.helpers.BrowserType
import selenide.helpers.BrowserUtils
import java.io.File
import java.net.URI

object Firefox {
    class CustomWebDriverProvider : WebDriverProvider {
        override fun createDriver(desiredCapabilities: DesiredCapabilities?): WebDriver {
            val service = GeckoDriverService.Builder()
                    .usingDriverExecutable(File(BrowserUtils.getDriverLocation(BrowserType.FIREFOX)))
                    .usingAnyFreePort()
                    .build()

            val options = FirefoxOptions()
            options.profile = createProfile(BrowserUtils.USE_QA_PROXY)
            options.setHeadless(BrowserUtils.USE_HEADLESS)

            return if (BrowserUtils.USE_GRID_HUB) {
                RemoteWebDriver(BrowserUtils.GRID_HUB_URL, options)
            } else {
                options.setBinary(BrowserUtils.FIREFOX_BINARY)
                FirefoxDriver(service, options)
            }
        }

        private fun createProfile(useProxy: Boolean = false): FirefoxProfile {
            val manualProxyFlag = 1
            val ignoreProxy = "localhost, 127.0.0.1"

            val profile = FirefoxProfile()
            if (useProxy) {
                val uri = URI(BrowserUtils.QA_PROXY)
                val proxyHost = uri.host
                val proxyPort = uri.port

                // Configures the same proxy for all variants
                profile.setPreference("network.proxy.type", manualProxyFlag)
                profile.setPreference("network.proxy.http", proxyHost)
                profile.setPreference("network.proxy.http_port", proxyPort)
                profile.setPreference("network.proxy.ssl", proxyHost)
                profile.setPreference("network.proxy.ssl_port", proxyPort)
                profile.setPreference("network.proxy.socks", proxyHost)
                profile.setPreference("network.proxy.socks_port", proxyPort)
                profile.setPreference("network.proxy.ftp", proxyHost)
                profile.setPreference("network.proxy.ftp_port", proxyPort)
                profile.setPreference("network.proxy.no_proxies_on", ignoreProxy)
            }

            return profile
        }
    }

}