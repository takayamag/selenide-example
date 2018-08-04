package selenide.pages.reserveapp.finalconfirm

import com.codeborne.selenide.SelenideElement
import com.codeborne.selenide.Selenide.`$`
import selenide.pages.PageBase

abstract class FinalConfirmPageBase : PageBase() {

    protected fun ErrorCheckResult(): SelenideElement {
        return `$`("#errorcheck_result")
    }

    protected fun ReturnToCheckInfo(): SelenideElement {
        return `$`("#returnto_checkInfo")
    }
}
