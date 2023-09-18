object Deps {

    val coreKtx by lazy { "androidx.core:core-ktx:${Versions.coreKtx}" }
    val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.appCompat}" }
    val material by lazy { "com.google.android.material:material:${Versions.material}" }
    val constraintLayout by lazy { "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}" }

    val splashScreen by lazy { "androidx.core:core-splashscreen:${Versions.splashScreen}" }

    val testJUint by lazy { "junit:junit:${Versions.testJUnit}" }
    val androidTestJUnit by lazy { "androidx.test.ext:junit:${Versions.androidTestJUnit}" }
    val androidTestExpresso by lazy { "androidx.test.espresso:espresso-core:${Versions.androidTestEspresso}" }
}