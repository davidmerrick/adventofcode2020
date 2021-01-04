group = "io.github.davidmerrick.aoc2020"

repositories {
    mavenCentral()
    jcenter()
}

plugins {
    kotlin("jvm") version "1.4.20"
}

dependencies {

    implementation("com.github.shiguruikai:combinatoricskt:1.6.0")
    implementation("com.google.guava:guava:30.1-jre")
    implementation("io.github.microutils:kotlin-logging:1.7.2")
    runtimeOnly("ch.qos.logback:logback-classic")

    // Test

    testImplementation("org.spekframework.spek2:spek-runner-junit5:2.0.8")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.6.2")
    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.3.2")
    testImplementation("io.mockk:mockk:1.10.0")
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }

    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }

    test {
        useJUnitPlatform()
    }
}
