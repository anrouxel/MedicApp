// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("io.gitlab.arturbosch.detekt") version("1.23.5")
    id("com.google.devtools.ksp") version("1.8.10-1.0.9") apply false
    id("io.objectbox") version("3.7.1") apply false
}
