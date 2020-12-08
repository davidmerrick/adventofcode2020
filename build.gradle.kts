group = "io.github.davidmerrick.aoc2020"

repositories {
  mavenCentral()
  jcenter()
}

plugins {
  kotlin("jvm") version "1.4.21"
}

dependencies {

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
