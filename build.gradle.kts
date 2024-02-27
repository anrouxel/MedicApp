// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("io.gitlab.arturbosch.detekt") version("1.23.5")
    id("com.google.devtools.ksp") version("1.8.10-1.0.9") apply false
}

val reportMerge by tasks.registering(io.gitlab.arturbosch.detekt.report.ReportMergeTask::class) {
    output.set(rootProject.layout.buildDirectory.file("reports/detekt/merge.xml")) // or "reports/detekt/merge.sarif"
}

subprojects {
    tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
        ignoreFailures = true
        reports {
            sarif.required.set(true)
        }
        finalizedBy(reportMerge)
    }

    reportMerge {
        input.from(tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().map { it.sarifReportFile })
    }
}