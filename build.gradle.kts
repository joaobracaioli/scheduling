import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.3.4.RELEASE"
	id("io.spring.dependency-management") version "1.0.10.RELEASE"
	kotlin("jvm") version "1.4.0"
	kotlin("plugin.spring") version "1.4.0"
}

group = "com.scheduling"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

ext["kotlinVersion"] = "1.4.0"

allprojects {
	repositories {
		mavenCentral()
		maven { url = uri("https://repo.spring.io/milestone") }
		maven { url = uri("https://repo.spring.io/snapshot") }
	}
}


subprojects {
	apply(plugin = "io.spring.dependency-management")
	apply(plugin = "kotlin")
	apply(plugin = "java")

	group = "com.scheduling"
	version = "0.0.1-SNAPSHOT"

	dependencies {
		implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.8")
		testImplementation("org.junit.jupiter:junit-jupiter-engine:5.5.2")
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "11"
		}
	}
}

configure<JavaPluginConvention> {
	sourceCompatibility = JavaVersion.VERSION_11
	targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}
