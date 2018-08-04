package selenide.helpers

enum class BrowserType(val value: String) {
    CHROME("CHROME"),
    CHROME_SP("CHROME_SP"),
    FIREFOX("FIREFOX"),
    IE("IE"),
    EDGE("EDGE"),
    SAFARI("SAFARI");
}

// テストブラウザー名を文字列で返す
fun getBrowserType(name: String): BrowserType {
    // ToDo: java.lang.IllegalArgumentException: No enum constant に対応する
    return BrowserType.valueOf(name.toUpperCase())
}
