
val jacksonVersion: String by rootProject.ext
val springBootVersion: String by rootProject.ext

dependencies {
    implementation("org.springframework:spring-web:5.3.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    implementation("org.springframework.boot:spring-boot-starter-validation:$springBootVersion")
}
