import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.4.0"
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

configurations["testRuntimeOnly"].extendsFrom(configurations.runtimeOnly.get())

dependencies {
    api(project(":api"))
    api(project(":controllers"))
    api(project(":core"))
    api(project(":gateway"))

    implementation("com.zaxxer:HikariCP:3.4.5")
    implementation("org.postgresql:postgresql")

    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework:spring-jdbc")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.test {
    useJUnitPlatform()
}
