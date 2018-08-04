package selenide.features

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import selenide.pages.reserveapp.inputform.InputPage
import com.codeborne.selenide.Selenide.screenshot
import junit.framework.TestCase.assertEquals

// gradle test --rerun-tasks --tests "selenide.features.ReserveAppSpec" -P TEST_BROWSER=CHROME
// gradle test --rerun-tasks --tests "selenide.features.ReserveAppSpec"

object SpecTest: Spek({
    describe("予約フォーム") {

        on("金曜日に2連泊する") {
            val inputPage = InputPage()

            it("予約が出来ること") {
                inputPage.open("http://example.selenium.jp/reserveApp/")
                screenshot("フォームを送信出来ること.png")

                inputPage.reserveYear = "2018"
                inputPage.reserveMonth = "8"
                inputPage.reserveDay = "24"
                inputPage.reserveTerm = "2"
                inputPage.setBreakfastOn()
                inputPage.guestName = "東京太郎"
                inputPage.isPlanA = false
                inputPage.isPlanB = false

                val checkPage = inputPage.clickGotoNext()
                assertEquals(checkPage.errorCheckResult, "")
                assertEquals(checkPage.dateFrom, "2018年8月24日")
                assertEquals(checkPage.dateTo, "2018年8月26日")
                assertEquals(checkPage.daysCount, "2")
                assertEquals(checkPage.headcount, "1")
                assertEquals(checkPage.bfOrder, "あり")
                assertEquals(checkPage.guestName, "東京太郎")
                assertEquals(checkPage.price, "17750")

                val finalPage = checkPage.doCommit()
                assertEquals(finalPage.errorCheckResult, "")
            }
        }

        on("三ヶ月以上先の予約を行う") {
            val inputPage = InputPage()

            it("予約ができないこと") {
                inputPage.open("http://example.selenium.jp/reserveApp/")
                screenshot("三ヶ月以上先の予約はできないこと.png")

                inputPage.reserveYear = "2020"
                inputPage.reserveMonth = "7"
                inputPage.reserveDay = "11"
                inputPage.reserveTerm = "2"
                inputPage.setBreakfastOn()
                inputPage.guestName = "東京 太郎"
                inputPage.isPlanA = true
                inputPage.isPlanB = true

                val checkPage = inputPage.clickGotoNext()
                assertEquals(checkPage.errorCheckResult, "宿泊日には、3ヶ月以内のお日にちのみ指定できます。")
            }
        }
    }
})