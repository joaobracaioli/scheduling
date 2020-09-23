plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

val coreLibVersion: String by rootProject.ext

dependencies {
    api(project(":core"))
    implementation("org.springframework.boot:spring-boot-starter-webflux")
}

val jar: Jar by tasks
val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks

bootJar.enabled = false
jar.enabled = true
