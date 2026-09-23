plugins {
    `java`
    `maven-publish`
}

group = "org.example"
version = "1.0-SNAPSHOT"
description = ""

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

repositories {
    mavenLocal()
    // CRITICAL: Tells Gradle where to fetch community plugins, libraries, and reactive frameworks
    mavenCentral()
    maven {
        url = uri("https://apache.org")
    }
}

dependencies {
    // Standard dependencies
    implementation("org.jetbrains:annotations:16.0.2")
    implementation("org.apache.commons:commons-lang3:3.18.0")

    // Project local submodules or dependencies
    implementation("org.example:MyIOUtils:1.0-SNAPSHOT")
    implementation("org.example:MyPostGreSqlClass:1.0-SNAPSHOT")

    // Reactive & Web (Now downloadable via mavenCentral)
    implementation("io.reactivex.rxjava2:rxjava:2.2.21")
    // Stable, patched Vert.x 4 coordinates that won't break your syntax
    implementation("io.vertx:vertx-web:4.5.31")
    implementation("io.vertx:vertx-rx-java2:4.5.31")
}
