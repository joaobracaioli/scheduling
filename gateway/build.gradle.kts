
dependencies {
    implementation(project(":core"))
    implementation("org.springframework:spring-jdbc:5.2.8.RELEASE")
}


val jar: Jar by tasks
jar.enabled = true
