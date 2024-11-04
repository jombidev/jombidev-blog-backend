import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "2.1.0-Beta2"
    kotlin("plugin.jpa") version "2.1.0-Beta2"
    kotlin("plugin.spring") version "2.1.0-Beta2"
    kotlin("plugin.serialization") version "2.1.0-Beta2"

    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management") version "1.1.5"
}

repositories {
    mavenCentral()
}

allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.jetbrains.kotlin.plugin.jpa")
    apply(plugin = "org.jetbrains.kotlin.plugin.serialization")

    group = "dev.jombi"
    version = "0.0.1-SNAPSHOT"

    dependencyManagement {
        dependencies {
            dependencySet("io.jsonwebtoken:0.12.6") {
                entry("jjwt-api")
                entry("jjwt-impl")
                entry("jjwt-jackson")
            }
        }
    }

    repositories {
        mavenCentral()
    }

    tasks.withType<KotlinCompile> {
        kotlin {
            jvmToolchain(17)
            compilerOptions {
                languageVersion.set(KotlinVersion.KOTLIN_2_1)
                apiVersion.set(KotlinVersion.KOTLIN_2_1)
                freeCompilerArgs.addAll("-Xjsr305=strict")
            }
        }
    }

    tasks.bootJar {
        enabled = true
    }

    dependencies {
        /// SPRING BOOT
        api("org.springframework.boot:spring-boot-starter-web") {
            exclude("org.springframework.boot", "spring-boot-starter-json")
        }
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-core-jvm:1.7.3")
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json-jvm:1.7.3")
        implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.1")

        /// KOTLIN
        implementation(kotlin("reflect"))

        /// TEST
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.springframework.security:spring-security-test")

        testImplementation(kotlin("test-junit5"))
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }
}

subprojects {
    dependencies {
        /// SPRING BOOT
        implementation("org.springframework.boot:spring-boot-starter-security")
        implementation("org.springframework.boot:spring-boot-starter-validation")
    }
}

dependencies {
    /*
    runtimeOnly("com.h2database:h2")

    */

    implementation(project(":api"))
    implementation(project(":business"))
    implementation(project(":core"))
    implementation(project(":infra"))

    runtimeOnly("com.mysql:mysql-connector-j")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.jar {
    enabled = false
}
