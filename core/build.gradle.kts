import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
}

val coreLibVersion: String by rootProject.ext

dependencies {
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

val jar: Jar by tasks
jar.enabled = true
