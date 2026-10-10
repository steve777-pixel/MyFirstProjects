plugins {
    java
    application 
}

java {
    toolchain {
        // Tells Gradle to handle compilation with Java 25 standards
        languageVersion.set(JavaLanguageVersion.of(25)) 
    }
}

group = "com.library"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral() 
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

application {
    mainClass.set("com.library.LibraryApplication")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}
