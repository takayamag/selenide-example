package selenide.pages.reserveapp.checkinfo

import com.codeborne.selenide.Selenide.page
import selenide.pages.reserveapp.finalconfirm.FinalConfirmPage
import selenide.pages.reserveapp.inputform.InputPage

class CheckInfoPage : CheckInfoPageBase() {

    val errorCheckResult: String
        get() = ErrorCheckResult().text()

    val price: String
        get() = Price().text()

    val dateFrom: String
        get() = DateFrom().text()

    val dateTo: String
        get() = DateTo().text()

    val daysCount: String
        get() = DaysCount().text()

    val headcount: String
        get() = Hc().text()

    val bfOrder: String
        get() = BfOrder().text()

    val planAOrder: String
        get() = PlanAOrder().text()

    val planBOrder: String
        get() = PlanBOrder().text()

    val guestName: String
        get() = GuestName().text()

    fun doCommit(): FinalConfirmPage {
        Commit().click()
        return page<FinalConfirmPage>(FinalConfirmPage::class.java)
    }

    fun doReturnToIndex(): InputPage {
        ReturnToIndex().click()
        return page<InputPage>(InputPage::class.java)
    }

}