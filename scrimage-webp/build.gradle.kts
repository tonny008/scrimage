plugins {
   `java-library`
   kotlin("jvm")
}

dependencies {
   api(project(":scrimage-core"))
   implementation("org.apache.commons:commons-lang3:3.11")
   implementation("org.slf4j:slf4j-api:1.7.36")

   testImplementation(kotlin("stdlib-jdk8"))
   testImplementation("io.kotest:kotest-assertions-core:5.3.2")
   testImplementation("io.kotest:kotest-runner-junit5:5.3.2")
}

java {
   sourceCompatibility = JavaVersion.VERSION_1_8
   targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.named<Test>("test") {
   useJUnitPlatform()
   filter {
      isFailOnNoMatchingTests = false
   }
   testLogging {
      showExceptions = true
      showStandardStreams = true
      events = setOf(
         org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
         org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED
      )
      exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
   }
}

val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
compileKotlin.kotlinOptions {
   jvmTarget = "1.8"
}

val compileTestKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
   jvmTarget = "1.8"
}

apply("../publish.gradle.kts")
