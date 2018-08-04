package selenide.helpers

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.WebDriverRunner
import org.openqa.selenium.Proxy
import org.openqa.selenium.WebDriver
import selenide.helpers.browsers.*
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

object BrowserUtils {
    // WebDriverファイルのあるディレクトリのルートパスを指定する
    private const val DRIVER_ROOT_PATH = "build/resources/test/drivers"

    // Binary path for Chrome
    // Chrome Stable: "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome"
    // Chrome Canary: "/Applications/Google Chrome Canary.app/Contents/MacOS/Google Chrome Canary"
    const val CHROME_BINARY: String = "/Applications/Google Chrome Canary.app/Contents/MacOS/Google Chrome Canary"

    // Binary path for Firefox
    // Firefox Stable = "/Applications/Firefox.app/Contents/MacOS/firefox-bin"
    // Firefox DeveloperEdition: "/Applications/FirefoxDeveloperEdition.app/Contents/MacOS/firefox-bin"
    const val FIREFOX_BINARY: String = "/Applications/FirefoxDeveloperEdition.app/Contents/MacOS/firefox-bin"

    const val TEST_DEVICE_NAME: String = "Nexus 5X"

    // プロキシサーバーのURL
    // null: 使用しない
    val QA_PROXY: String? = System.getProperty("QA_PROXY")
    val USE_QA_PROXY: Boolean = !QA_PROXY.isNullOrEmpty()

    // Webブラウザーの名前を指定する
    private val browserName = System.getProperty("TEST_BROWSER") ?: "CHROME"
    private val TEST_BROWSER: BrowserType = getBrowserType(browserName)

    // Headlessモードにするかどうか(ChromeかFirefoxに限る)
    private val useHeadless: String? = System.getProperty("USE_HEADLESS")
    val USE_HEADLESS: Boolean = !useHeadless.isNullOrEmpty()

    // Selenium Grid HubのURL
    private val hubUrl = System.getProperty("GRID_HUB_URL") ?: "http://127.0.0.1:4444/wd/hub"
    val GRID_HUB_URL: URL = URL(hubUrl)

    private val useGridHub: String? = System.getProperty("USE_GRID_HUB")
    val USE_GRID_HUB: Boolean = !useGridHub.isNullOrEmpty()

    // Reportの出力先ディレクトリ名を設定する: build/reports/tests/$REPORT_DIR_NAME
    private val REPORT_DIR_NAME = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())

    /**
     * SelenideのConfigurationを行う
     * WebDriverの制御は可能な限りSelenideのConfigurationプロパティを活用する
     */
    private fun configureSelenide() {
        // Refer to: https://github.com/codeborne/selenide/blob/master/src/main/java/com/codeborne/selenide/Configuration.java

        Configuration.collectionsTimeout = 6000 // Default value: 6000 (milliseconds)
        Configuration.timeout = 6000 // Default value: 4000 (milliseconds)
        Configuration.pollingInterval = 500 // Default value: 100 (milliseconds)
        Configuration.collectionsPollingInterval = 200 // Default value: 200 (milliseconds)
        Configuration.holdBrowserOpen = false // Default value: false.
        Configuration.reopenBrowserOnFail = true // Default value: true
        Configuration.openBrowserTimeoutMs = 15000 // Default value: 15000 (milliseconds)
        Configuration.closeBrowserTimeoutMs = 5000 // Default value: 5000 (milliseconds)

        Configuration.browserVersion = null // Default value: none, Which browser version to use (for Internet Explorer).
        Configuration.browserSize = "1024x768" // Default value: none (browser size will not be set explicitly)
        Configuration.startMaximized = false // Default value: true

        Configuration.clickViaJs = false // Default value: false
        Configuration.captureJavascriptErrors = true // Default value: true
        Configuration.screenshots = false // Default value: true

        Configuration.reportsFolder = "build/reports/tests/$REPORT_DIR_NAME" // Default value: "build/reports/tests" (this is default for Gradle projects)
        Configuration.dismissModalDialogs = false // Default value: false

        Configuration.fastSetValue = false // Default value: false

        Configuration.selectorMode = Configuration.SelectorMode.CSS // Default Selenium behavior is CSS
        Configuration.assertionMode = Configuration.AssertionMode.STRICT // Default mode is STRICT - tests are failing immediately
        Configuration.fileDownload = Configuration.FileDownloadMode.HTTPGET // Default: HTTPGET
        Configuration.driverManagerEnabled = true // Default: true

        /**
         * `normal`: return after the load event fires on the new page (it's default in Selenium webdriver);
         * `eager`: return after DOMContentLoaded fires;
         * `none`: return immediately
         */
        Configuration.pageLoadStrategy = "normal" // Default value: "normal".

        Configuration.browser = getCustomWebDriverProvider(TEST_BROWSER)
    }

    /*
        各種ブラウザーを起動する
     */
    fun launchBrowser(): WebDriver {
        // デバッグ用
        println("QA_PROXY: $QA_PROXY")
        println("USE_QA_PROXY: $USE_QA_PROXY")
        println("TEST_BROWSER: $TEST_BROWSER")
        println("USE_HEADLESS: $USE_HEADLESS")
        println("GRID_HUB_URL: $GRID_HUB_URL")
        println("USE_GRID_HUB: $USE_GRID_HUB")

        // SelenideのConfigurationを行う
        configureSelenide()

        // EventListenerを追加する

        // 以下の設定方法はSelenideからWebDriverを正しく扱えなくなるため使用しない
        // val efWebDriver = EventFiringWebDriver(driver)
        // val listener = EventListener()
        // efWebDriver.register(listener)
        // WebDriverRunner.setWebDriver(efWebDriver)

        // EventFiringWebDriverは使用せずに次のようにリスナーを追加する
        WebDriverRunner.addListener(EventListener())

        // ToDo: 動作するかどうか調べる
        // WebDriverRunner.clearBrowserCache()

        return WebDriverRunner.getWebDriver()
    }

    // WebBrowserを明示的に終了する
    fun closeBrowser(driver: WebDriver?) {
        driver?.quit()
    }

    /**
     *  各WebDriverのCustomWebDriverProviderの名前を返す
     *  refer to: https://github.com/codeborne/selenide/wiki/How-Selenide-creates-WebDriver
     */
    private fun getCustomWebDriverProvider(browserType: BrowserType): String {
        when (browserType) {
            BrowserType.CHROME -> {
                return Chrome.CustomWebDriverProvider::class.java.name
            }
            BrowserType.CHROME_SP -> {
                return ChromeSp.CustomWebDriverProvider::class.java.name
            }
            BrowserType.FIREFOX -> {
                return Firefox.CustomWebDriverProvider::class.java.name
            }
            BrowserType.IE -> {
                return IE.CustomWebDriverProvider::class.java.name
            }
            BrowserType.EDGE -> {
                return Edge.CustomWebDriverProvider::class.java.name
            }
            BrowserType.SAFARI -> {
                return Safari.CustomWebDriverProvider::class.java.name
            }
        }
    }

    /*
        ChromeとIEのプロキシー設定を得る
     */
    fun getProxy(): Proxy {

        val proxy = Proxy()
        val url = QA_PROXY
        proxy.setHttpProxy(url).setFtpProxy(url).sslProxy = url

        return proxy
    }

    /*
        WebDriverファイルのある場所のフルパスを得る
     */
    fun getDriverLocation(browserType: BrowserType): String {

        // Set default value
        var browserFolder = "chrome"
        var operatingSystem = "windows"
        var machineBits = "32bit"
        var executableFile = "chromedriver.exe"


        when (browserType) {
            BrowserType.CHROME -> {
                browserFolder = "chrome"
                executableFile = "chromedriver.exe"
                if (CommonUtils.isMac()) {
                    machineBits = "64bit"
                }
            }
            BrowserType.CHROME_SP -> {
                browserFolder = "chrome"
                executableFile = "chromedriver.exe"
                if (CommonUtils.isMac()) {
                    machineBits = "64bit"
                }
            }
            BrowserType.FIREFOX -> {
                browserFolder = "firefox"
                executableFile = "geckodriver.exe"
                machineBits = "64bit"
            }
            BrowserType.IE -> {
                browserFolder = "ie"
                executableFile = "IEDriverService.exe"
            }
            BrowserType.EDGE -> {
                browserFolder = "edge"
                executableFile = "MicrosoftWebDriver.exe"
                machineBits = "64bit"
            }
            else -> {
                // TODO: 指定されたブラウザーの種別が存在しない場合
            }
        }

        // macOSの時は「.exe」の拡張子を削除する
        if (CommonUtils.isMac()) {
            operatingSystem = "mac"
            executableFile = executableFile.replace(".exe", "")
        }

        return "$DRIVER_ROOT_PATH/$browserFolder/$operatingSystem/$machineBits/$executableFile"
    }
}