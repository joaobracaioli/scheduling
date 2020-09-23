plugins {
    id("io.spring.dependency-management")
}

val jacksonVersion: String by rootProject.ext
val springBootVersion: String by rootProject.ext

dependencies {
    implementation(project(":core"))
}


val jar: Jar by tasks
jar.enabled = true
