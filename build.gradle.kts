import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.6.7"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
}

group = "com.cbr.ilk"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign:3.1.2")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
//	runtimeOnly("org.postgresql:postgresql")
	implementation("org.camunda.bpm.springboot:camunda-bpm-spring-boot-starter-rest:7.17.0")
	implementation("io.github.microutils:kotlin-logging:1.6.24")
	implementation("org.camunda.bpm.springboot:camunda-bpm-spring-boot-starter-webapp:3.2.0")
	implementation("org.apache.commons:commons-csv:1.6")
	implementation("org.camunda.bpm.springboot:camunda-bpm-spring-boot-starter-rest:3.2.0")
	implementation("org.camunda.bpm.extension.springboot:camunda-bpm-spring-boot-starter-test:2.2.0")
//	implementation("org.assertj:assertj-core:2.6.0")
//	implementation("org.camunda.bpm.assert:camunda-bpm-assert:3.0.0-alpha1")
//	implementation("org.camunda.bpm.assert:camunda-bpm-assert:3.0.0")
//	implementation("org.camunda.spin:camunda-spin-dataformat-json-jackson:1.6.7")
//	implementation("org.camunda.spin:camunda-spin-core:1.6.7")
//	implementation("org.camunda.bpm:camunda-engine-plugin-spin:7.10.0")
////	implementation("com.zaxxer:HikariCP:2.4.6")
//	implementation("com.github.seratch:kotliquery:1.1.1")
//	implementation("com.beust:klaxon:5.5")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.1")
	testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
	implementation("com.h2database:h2")
	implementation("org.camunda.spin:camunda-spin-dataformat-all:1.16.0")
	testImplementation("org.camunda.bpm.extension:camunda-bpm-assert-scenario:0.2")
	testImplementation("org.slf4j:jul-to-slf4j")
	implementation("com.camunda.consulting:camunda-bpm-simulator:1.1.1")

	implementation("org.projectlombok:lombok:1.18.18")
	implementation("org.camunda.bpm.extension.migration:camunda-bpm-migration:1.0")


}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
