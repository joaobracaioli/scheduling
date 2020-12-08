import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("io.spring.dependency-management") version "1.0.10.RELEASE"
	kotlin("jvm") version "1.4.10"
	kotlin("plugin.spring") version "1.4.10"
	java
	idea
}

group = "com.scheduling"
version = "0.0.1-SNAPSHOT"

ext["kotlinVersion"] = "1.4.0"
ext["springBootVersion"] = "2.3.4.RELEASE"
ext["jacksonVersion"] = "2.11.0"

allprojects {
	repositories {
		jcenter()
		mavenLocal()
		mavenCentral()
	}
}

subprojects {
	apply(plugin = "kotlin")
	apply(plugin = "idea")
	apply(plugin = "org.jetbrains.kotlin.kapt")
	apply(plugin = "org.jetbrains.kotlin.plugin.allopen")

	group = "com.scheduling"
	version = "0.0.1-SNAPSHOT"

	dependencies {
		// implementation("org.apache.commons:commons-lang3:3.10")

		implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.8")
		testImplementation("org.mockito:mockito-inline:3.4.6")
		testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
		testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.1")
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
