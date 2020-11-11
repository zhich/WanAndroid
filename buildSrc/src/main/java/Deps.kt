/**
 * Created by zch on 2020-10-21.
 */
object Versions {
    const val compileSdk = 30
    const val minSdk = 21
    const val targetSdk = 30
    const val versionCode = 1
    const val versionName = "1.0"

    const val kotlin = "1.4.10"
    const val coroutines_android = "1.4.0-M1"
    const val androidxArch = "2.0.0"
    const val mockito = "2.23.0"

    const val appcompat = "1.2.0"
    const val constraintLayout = "2.0.2"
    const val material = "1.2.1"
    const val core_ktx = "1.3.2"
    const val viewmodel_ktx = "2.2.0"

    const val retrofit = "2.9.0"
    const val retrofit_converter_gson = "2.9.0"
    const val okhttp_logging_interceptor = "4.9.0"
    const val koin = "2.2.0-rc-3"
    const val immersionbar = "3.0.0"
    const val banner = "2.1.0"
}

object Deps {
    // androidx
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx}"
    const val viewmodel_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewmodel_ktx}"

    // kotlin
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_android}"

    // network
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofit_converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit_converter_gson}"
    const val okhttp_logging_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp_logging_interceptor}"

    // third
    const val koin_android = "org.koin:koin-android:${Versions.koin}"
    const val koin_androidx_scope = "org.koin:koin-androidx-scope:${Versions.koin}"
    const val koin_androidx_viewmodel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    const val immersionbar = "com.gyf.immersionbar:immersionbar:${Versions.immersionbar}"
    const val banner = "com.youth.banner:banner:${Versions.banner}"
}