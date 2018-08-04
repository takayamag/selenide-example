package selenide.pages.reserveapp.checkinfo

import com.codeborne.selenide.SelenideElement
import com.codeborne.selenide.Selenide.`$`
import selenide.pages.PageBase

abstract class CheckInfoPageBase : PageBase() {

    protected fun ErrorCheckResult(): SelenideElement {
        return `$`("#errorcheck_result")
    }

    protected fun Price(): SelenideElement {
        return `$`("#price")
    }

    protected fun DateFrom(): SelenideElement {
        return `$`("#datefrom")
    }

    protected fun DateTo(): SelenideElement {
        return `$`("#dateto")
    }

    protected fun DaysCount(): SelenideElement {
        return `$`("#dayscount")
    }

    protected fun Hc(): SelenideElement {
        return `$`("#hc")
    }

    protected fun BfOrder(): SelenideElement {
        return `$`("#bf_order")
    }

    protected fun PlanAOrder(): SelenideElement {
        return `$`("#plan_a_order")
    }

    protected fun PlanBOrder(): SelenideElement {
        return `$`("#plan_b_order")
    }

    protected fun GuestName(): SelenideElement {
        return `$`("#gname")
    }

    protected fun Commit(): SelenideElement {
        return `$`("#commit")
    }

    protected fun ReturnToIndex(): SelenideElement {
        return `$`("#returnto_index")
    }

}