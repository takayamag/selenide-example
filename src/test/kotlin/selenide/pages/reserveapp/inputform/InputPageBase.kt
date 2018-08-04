package selenide.pages.reserveapp.inputform

import com.codeborne.selenide.SelenideElement
import com.codeborne.selenide.Selenide.*
import selenide.pages.PageBase

abstract class InputPageBase : PageBase() {

    protected fun ReserveYear(): SelenideElement {
        return `$`("#reserve_year")
    }

    protected fun ReserveMonth(): SelenideElement {
        return `$`("#reserve_month")
    }

    protected fun ReserveDay(): SelenideElement {
        return `$`("#reserve_day")
    }

    protected fun ReserveTerm(): SelenideElement {
        return `$`("#reserve_term")
    }

    protected fun Headcount(): SelenideElement {
        return `$`("#headcount")
    }

    protected fun BreakfastOn(): SelenideElement {
        return `$`("#breakfast_on")
    }

    protected fun BreakfastOff(): SelenideElement {
        return `$`("#breakfast_off")
    }

    protected fun PlanA(): SelenideElement {
        return `$`("#plan_a")
    }

    protected fun PlanB(): SelenideElement {
        return `$`("#plan_b")
    }

    protected fun GuestName(): SelenideElement {
        return `$`("#guestname")
    }

    protected fun GotoNext(): SelenideElement {
        return `$`("#goto_next")
    }
}