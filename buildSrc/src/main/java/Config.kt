import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

object Config {

    const val appId = "com.khalore.snapwords"
    const val appName = "SnapWords"
    var archivesBaseName = "$appName-${Versions.versionName}_${Versions.versionCode})}"

    const val isMinifyEnabled = false

    object Sdk {
        const val minSdk = 26
        const val targetSdk = 34
        const val compileSdk = 34
    }

    object SignIn {
        const val keyAlias = "key"
        const val keyPassword = "android"
        const val storeFilePath = "../key.jks"
        const val storePassword = "android"
    }

    object SignInDebug {
        const val keyAlias = "android"
        const val keyPassword = "android"
        const val storeFilePath = "../debug_key.jks"
        const val storePassword = "android"
    }

    object Versions {
        private const val versionMajor = 1
        private const val versionMinor = 0
        private const val versionPatch = 0

        const val versionCode = 1
        const val versionName = "${versionMajor}.${versionMinor}.${versionPatch}"
    }

}
