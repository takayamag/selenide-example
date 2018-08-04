package selenide.helpers

object CommonUtils {

    fun is64BitMachine(): Boolean {
        val bit = System.getProperty("os.arch")
        return bit.contains("64")
    }

    fun isMac(): Boolean {
        val os = System.getProperty("os.name").toLowerCase()
        return os.startsWith("mac") || os.contains("mac")
    }

    fun isLinux(): Boolean {
        val os = System.getProperty("os.name").toLowerCase()
        return os.contains("linux")
    }

    fun isWindows(): Boolean {
        val os = System.getProperty("os.name").toLowerCase()
        return os.indexOf("win") > 0
    }

    // 環境変数を取得する
    fun getEnv(name: String, defaultValue: String): String = if (System.getenv(name).isNullOrEmpty()) defaultValue else System.getenv(name)
}