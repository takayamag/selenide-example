package selenide.helpers.browsers

import com.codeborne.selenide.WebDriverProvider
import org.openqa.selenium.PageLoadStrategy
import org.openqa.selenium.WebDriver
import org.openqa.selenium.edge.EdgeDriver
import org.openqa.selenium.edge.EdgeDriverService
import org.openqa.selenium.edge.EdgeOptions
import org.openqa.selenium.remote.CapabilityType
import org.openqa.selenium.remote.DesiredCapabilities
import selenide.helpers.BrowserType
import selenide.helpers.BrowserUtils
import java.io.File

object Edge {
    class CustomWebDriverProvider : WebDriverProvider {
        override fun createDriver(desiredCapabilities: DesiredCapabilities?): WebDriver {
            val service = EdgeDriverService.Builder()
                    .usingDriverExecutable(File(BrowserUtils.getDriverLocation(BrowserType.EDGE)))
                    .usingAnyFreePort()
                    .build()

            /*
            val caps = DesiredCapabilities.internetExplorer()
            caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true)  // 設定が効いていない気がする
            caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION,true)
            caps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, false)
            // caps.getCapabilities(InternetExplorerDriver.IE_USE_PER_PROCESS_PROXY, true)
            if (BrowserUtils.USE_PROXY_FLAG) {
                caps.setCapability(CapabilityType.PROXY, BrowserUtils.getProxy())
            }
            */

            val options = EdgeOptions()
            // options.setPageLoadStrategy("NORMAL")

            // プロキシを設定する
            if (BrowserUtils.USE_QA_PROXY) {
                options.setProxy(BrowserUtils.getProxy())
            }

            return EdgeDriver(service, options)
        }
    }
}