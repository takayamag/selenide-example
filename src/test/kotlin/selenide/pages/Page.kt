package selenide.pages

import com.codeborne.selenide.Selenide
import org.openqa.selenium.Cookie
import org.openqa.selenium.WebDriver
import selenide.helpers.BrowserUtils.launchBrowser

interface Page {

    fun open(url: String, cookies: List<Cookie>? = null): WebDriver {
        val webDriver = launchBrowser()

        cookies?.forEach { webDriver.manage().addCookie(it) }
        Selenide.open(url)

        return webDriver
    }
}
