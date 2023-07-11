plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.google.gms.google-services")
//    id("app.cash.sqldelight") version "2.0.0-rc01"
}

repositories {
    google()
    mavenCentral()
}
//
//sqldelight {
//    databases {
//        create("Database") {
//            packageName.set("com.example")
//        }
//    }
//}

kotlin {

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    jvm("desktop"){
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }


    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val ktorVersion = "2.3.1"
        val commonMain by getting{
            dependencies {
//                implementation("app.cash.sqldelight:native-driver:2.0.0-rc01")
//                implementation("app.cash.sqldelight:sqlite-driver:2.0.0-rc01")
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
                implementation("io.ktor:ktor-client-cio:$ktorVersion")
                implementation ("com.google.code.gson:gson:2.10.1")


            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting{
            dependencies{
                implementation("app.cash.sqldelight:android-driver:2.0.0-rc01")
            }
        }
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }

        val desktopMain by getting{
            dependencies {
                implementation("androidx.navigation:navigation-compose:2.6.0")
//                implementation("app.cash.sqldelight:sqlite-driver:2.0.0-rc01")
                implementation("io.ktor:ktor-client-cio:$ktorVersion")
                implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
                implementation ("io.ktor:ktor-client-serialization:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation ("com.google.code.gson:gson:2.10.1")




            }
        }




    }
}

android {
    namespace = "com.example.myapplication"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}





