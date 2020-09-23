val jacksonVersion: String by rootProject.ext
val springBootVersion: String by rootProject.ext

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion") {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
    }
    implementation("com.fasterxml.jackson.module", "jackson-module-kotlin", jacksonVersion)
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.4.10"))

}



