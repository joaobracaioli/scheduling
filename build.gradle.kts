import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	id("org.springframework.boot") version "2.3.4.RELEASE"
	id("io.spring.dependency-management") version "1.0.10.RELEASE"
	kotlin("jvm") version "1.4.0"
	kotlin("plugin.spring") version "1.4.0"
	idea
}

group = "com.scheduling"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

ext["kotlinVersion"] = "1.4.0"
ext["springBootVersion"] = "2.3.4.RELEASE"
ext["jacksonVersion"] = "2.11.0"


allprojects {
	repositories {
		mavenCentral()
		jcenter()
		maven { url = uri("https://repo.spring.io/milestone") }
		maven { url = uri("https://repo.spring.io/snapshot") }
	}
}


subprojects {
	apply(plugin = "io.spring.dependency-management")
	apply(plugin = "kotlin")
	apply(plugin = "java")
	apply(plugin = "idea")

	group = "com.scheduling"
	version = "0.0.1-SNAPSHOT"

	dependencies {
		implementation("org.apache.commons:commons-lang3:3.10")

		implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.8")
		testImplementation("org.junit.jupiter:junit-jupiter-engine:5.5.2")
	}
}

configure<JavaPluginConvention> {
	sourceCompatibility = JavaVersion.VERSION_11
	targetCompatibility = JavaVersion.VERSION_11
}



val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true

defaultTasks("clean", "build")
