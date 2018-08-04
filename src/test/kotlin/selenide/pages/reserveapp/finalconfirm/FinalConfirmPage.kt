package selenide.pages.reserveapp.finalconfirm

class FinalConfirmPage : FinalConfirmPageBase() {

  val errorCheckResult: String
    get() = ErrorCheckResult().text()

}
