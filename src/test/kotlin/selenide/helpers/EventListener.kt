package selenide.helpers

import org.openqa.selenium.By
import org.openqa.selenium.OutputType
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.events.WebDriverEventListener

// http://toolsqa.com/selenium-webdriver/event-listener/
class EventListener : WebDriverEventListener {

    /**
     * Called before {@link org.openqa.selenium.TakesScreenshot#getScreenshotAs(OutputType)}
     * allows the implementation to determine which type of output will be generated
     */
    override fun <X> beforeGetScreenshotAs(target: OutputType<X>) {}

    /**
     * Called after {@link org.openqa.selenium.TakesScreenshot#getScreenshotAs(OutputType)}
     * allows the implementation to determine which type of output was generated
     * and to access the output itself
     */
    override fun <X> afterGetScreenshotAs(target: OutputType<X>, screenshot: X) {}

    /**
     * Called before {@link WebElement#getText()} method is being called
     */
    override fun beforeGetText(element: WebElement, driver: WebDriver) {}

    /**
     * Called right after {@link WebElement#getText()} method is being called
     */
    override fun afterGetText(element: WebElement, driver: WebDriver, text: String) {}

    /**
     * This action will be performed each time before [Alert.accept]
     */
    override fun beforeAlertAccept(driver: WebDriver) {}

    /**
     * This action will be performed each time after [Alert.accept]
     */
    override fun afterAlertAccept(driver: WebDriver) {}

    /**
     * This action will be performed each time before [Alert.dismiss]
     */
    override fun afterAlertDismiss(driver: WebDriver) {}

    /**
     * This action will be performed each time after [Alert.dismiss]
     */
    override fun beforeAlertDismiss(driver: WebDriver) {}

    /**
     * Called before [get(String url)][org.openqa.selenium.WebDriver.get] respectively
     * [navigate().to(String url)][org.openqa.selenium.WebDriver.Navigation.to].
     */
    override fun beforeNavigateTo(url: String, driver: WebDriver) {
        System.out.println("次のURLへ遷移します: " + url)
    }

    /**
     * Called after [get(String url)][org.openqa.selenium.WebDriver.get] respectively
     * [navigate().to(String url)][org.openqa.selenium.WebDriver.Navigation.to]. Not called, if an
     * exception is thrown.
     */
    override fun afterNavigateTo(url: String, driver: WebDriver) {
        System.out.println("次のURLへ遷移しました: " + url)
    }

    /**
     * Called before [navigate().back()][org.openqa.selenium.WebDriver.Navigation.back].
     */
    override fun beforeNavigateBack(driver: WebDriver) {
        System.out.println("ブラウザーの「戻る」を実行します: " + driver.currentUrl)
    }

    /**
     * Called after [navigate().back()][org.openqa.selenium.WebDriver.Navigation]. Not called, if an
     * exception is thrown.
     */
    override fun afterNavigateBack(driver: WebDriver) {
        System.out.println("ブラウザーの「戻る」を実行しました: " + driver.currentUrl)
    }

    /**
     * Called before [navigate().forward()][org.openqa.selenium.WebDriver.Navigation.forward].
     */
    override fun beforeNavigateForward(driver: WebDriver) {
        System.out.println("ブラウザーの「進む」を実行します: " + driver.currentUrl)
    }

    /**
     * Called after [navigate().forward()][org.openqa.selenium.WebDriver.Navigation.forward]. Not called,
     * if an exception is thrown.
     */
    override fun afterNavigateForward(driver: WebDriver) {
        System.out.println("ブラウザーの「進む」を実行しました: " + driver.currentUrl)
    }

    /**
     * Called before [navigate().refresh()][org.openqa.selenium.WebDriver.Navigation.refresh].
     */
    override fun beforeNavigateRefresh(driver: WebDriver) {}

    /**
     * Called after [navigate().refresh()][org.openqa.selenium.WebDriver.Navigation.refresh]. Not called,
     * if an exception is thrown.
     */
    override fun afterNavigateRefresh(driver: WebDriver) {}

    /**
     * Called before [WebDriver.findElement(...)][WebDriver.findElement], or
     * [WebDriver.findElements(...)][WebDriver.findElements],
     * or [ WebElement.findElement(...)][WebElement.findElement],
     * or [WebElement.findElements(...)][WebElement.findElement].
     */
    override fun beforeFindBy(by: By, element: WebElement?, driver: WebDriver) {
        System.out.println("次の要素を探します: " + by.toString())
    }

    /**
     * Called after [WebDriver.findElement(...)][WebDriver.findElement], or
     * [WebDriver.findElements(...)][WebDriver.findElements],
     * or [ WebElement.findElement(...)][WebElement.findElement],
     * or [WebElement.findElements(...)][WebElement.findElement].
     */
    override fun afterFindBy(by: By, element: WebElement?, driver: WebDriver) {
        System.out.println("次の要素が見つかりました: $by")
    }

    /**
     * Called before [WebElement.click()][WebElement.click].
     */
    override fun beforeClickOn(element: WebElement, driver: WebDriver) {
        System.out.println("次の要素をクリックします: ${element}")
    }

    /**
     * Called after [WebElement.click()][WebElement.click]. Not called, if an exception is
     * thrown.
     */
    override fun afterClickOn(element: WebElement, driver: WebDriver) {
        System.out.println("次の要素をクリックしました: " + element.toString())
    }

    /**
     * Called before [WebElement.clear()][WebElement.clear], [ WebElement.sendKeys(...)][WebElement.sendKeys].
     */
    override fun beforeChangeValueOf(element: WebElement, driver: WebDriver, keysToSend: Array<CharSequence>?) {
        // System.out.println("Inside the beforeChangeValueOf method")
    }

    /**
     * Called after [WebElement.clear()][WebElement.clear], [ WebElement.sendKeys(...)][WebElement.sendKeys]}. Not called, if an exception is thrown.
     */
    override fun afterChangeValueOf(element: WebElement, driver: WebDriver, keysToSend: Array<CharSequence>?) {
        // System.out.println("inside method afterChangeValueOf on " + element.toString())
    }

    /**
     * Called before [org.openqa.selenium.remote.RemoteWebDriver.executeScript]
     */
    // Previously: Called before {@link WebDriver#executeScript(String)}
    // See the same issue below.
    override fun beforeScript(script: String, driver: WebDriver) {
       //  System.out.println("次のスクリプトを実行します: " + script)
    }

    /**
     * Called after [org.openqa.selenium.remote.RemoteWebDriver.executeScript].
     * Not called if an exception is thrown
     */
    // Previously: Called after {@link WebDriver#executeScript(String)}. Not called if an exception is thrown
    // So someone should check if this is right.  There is no executeScript method
    // in WebDriver, but there is in several other places, like this one
    override fun afterScript(script: String, driver: WebDriver) {
        // System.out.println("次のスクリプトを実行しました: " + script)
    }

    /**
     * Called whenever an exception would be thrown.
     */
    override fun onException(throwable: Throwable, driver: WebDriver) {
        System.out.println("例外が発生しました: " + throwable.message)
    }

    override fun beforeSwitchToWindow(windowName: String, driver: WebDriver) {
        // TODO("not implemented")
        // To change body of created functions use File | Settings | File Templates.
    }

    override fun afterSwitchToWindow(windowName: String, driver: WebDriver) {
        // TODO("not implemented")
        // To change body of created functions use File | Settings | File Templates.
    }
}