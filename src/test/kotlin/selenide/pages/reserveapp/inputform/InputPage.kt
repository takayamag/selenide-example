package selenide.pages.reserveapp.inputform

import com.codeborne.selenide.Selenide.*
import com.codeborne.selenide.Condition.*
import selenide.pages.reserveapp.checkinfo.CheckInfoPage

class InputPage : InputPageBase() {

    var reserveYear: String
        get() = ReserveYear().`val`()
        set(year) {
            ReserveYear().`val`(year)
        }

    var reserveMonth: String
        get() = ReserveMonth().`val`()
        set(month) {
            ReserveMonth().`val`(month)
        }

    var reserveDay: String
        get() = ReserveDay().`val`()
        set(day) {
            ReserveDay().`val`(day)
        }

    var reserveTerm: String
        get() = ReserveTerm().`val`()
        set(term) {
            ReserveTerm().`val`(term)
        }

    var headcount: String
        get() = Headcount().`val`()
        set(count) {
            Headcount().`val`(count)
        }

    val isBreakfastOn: Boolean
        get() = BreakfastOn().`is`(selected)

    fun setBreakfastOn() {
        BreakfastOn().click()
    }

    val isBreakfastOff: Boolean
        get() = BreakfastOff().`is`(selected)

    fun setBreakfastOff() {
        BreakfastOff().click()
    }

    var isPlanA: Boolean
        get() = PlanA().`is`(selected)
        set(check) {
            PlanA().isSelected = check
        }

    var isPlanB: Boolean
        get() = PlanB().`is`(selected)
        set(check) {
            PlanB().isSelected = check
        }

    var guestName: String
        get() = GuestName().`val`()
        set(name) {
            GuestName().`val`(name)
        }

    fun clickGotoNext(): CheckInfoPage {
        GotoNext().click()
        return page(CheckInfoPage::class.java)
    }

}