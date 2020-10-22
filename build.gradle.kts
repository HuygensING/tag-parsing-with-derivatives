import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.0"
}

repositories {
    // Use jcenter for resolving dependencies.
    // You can declare any Maven/Ivy/file repository here.
    jcenter()
    maven {
        url = uri("http://maven.huygens.knaw.nl/repository")
    }
    maven {
        url = uri("https://dl.bintray.com/kotlin/kotlin-dev")
    }
    gradlePluginPortal()
}

dependencies {
    compileOnly("javax.servlet:javax.servlet-api:3.1.0")
    compileOnly("javax.servlet.jsp:javax.servlet.jsp-api:2.3.1")

    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // Use parsec.
    implementation("lambdada:parsec:1.0")

    // Use Arrow for fp
    val arrowVersion = "0.10.2"
    implementation("io.arrow-kt:arrow-core:${arrowVersion}")
    implementation("io.arrow-kt:arrow-core-data:${arrowVersion}")

    // https://github.com/eclipse/lsp4j
    val lspVersion = "0.6.0" // LSP 3.14
//    val lspVersion = "0.8.1" // LSP 3.15
    implementation("org.eclipse.lsp4j:org.eclipse.lsp4j:${lspVersion}")
    implementation("org.eclipse.lsp4j:org.eclipse.lsp4j.jsonrpc:${lspVersion}")
//    implementation("org.eclipse.lsp4j:org.eclipse.lsp4j.generator:${lsp_version}")
    implementation("org.eclipse.lsp4j:org.eclipse.lsp4j.debug:${lspVersion}")
//    implementation("org.eclipse.lsp4j:org.eclipse.lsp4j.jsonrpc.debug:${lsp_version}")
//    implementation("org.eclipse.lsp4j:org.eclipse.lsp4j.websocket:${lspVersion}")

    // https://github.com/Kotlin/kotlinx.cli
    implementation("org.jetbrains.kotlinx:kotlinx-cli-jvm:0.2.0-dev-7")

//    implementation("javax.servlet:javax.servlet-api:4.0.1")
    implementation("nl.knaw.huygens.alexandria:alexandria-markup-core:2.3.2-SNAPSHOT")

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    testImplementation("org.assertj:assertj-core:3.12.2")
    testImplementation("io.github.microutils:kotlin-logging:1.7.7")
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}