plugins {
    kotlin("jvm") version "1.3.71"
    id("org.jlleitschuh.gradle.ktlint") version "9.2.1"
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://dl.bintray.com/mipt-npm/scientifik")
    maven("https://dl.bintray.com/kotlin/kotlin-numpy")
    maven("https://jetbrains.bintray.com/lets-plot-maven")
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    val ktorVersion = "1.3.0"
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-gson:$ktorVersion")
    implementation("io.ktor:ktor-freemarker:$ktorVersion")
    implementation("io.ktor:ktor-html-builder:$ktorVersion")

    val kotlinx_html_version = "0.7.1"
    // Server Side
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:$kotlinx_html_version")
    // Client Side
    implementation("org.jetbrains.kotlinx:kotlinx-html-js:$kotlinx_html_version")

    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.3.0")
    testImplementation("io.ktor:ktor-server-test-host:$ktorVersion")
    testImplementation("org.slf4j:slf4j-simple:1.7.26")

    val kmathVersion = "0.1.3"
    api("scientifik:kmath-core:$kmathVersion")

    val knumpyVersion = "0.1.4"
    implementation("org.jetbrains:kotlin-numpy:$knumpyVersion")

    val letsPlotVersion = "0.0.9-SNAPSHOT"
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.lets-plot:lets-plot-common:1.3.1")
    api("org.jetbrains.lets-plot:lets-plot-kotlin-api:$letsPlotVersion")

    implementation("com.google.api-client:google-api-client:1.30.4")
    implementation("com.google.oauth-client:google-oauth-client-jetty:1.30.4")
    implementation("com.google.apis:google-api-services-sheets:v4-rev581-1.25.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.4")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

val mainClass = "KotlinDataScience.MainKt"
tasks.jar {
    manifest {
        attributes["Main-Class"] = mainClass
    }
}
