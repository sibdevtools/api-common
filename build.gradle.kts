import java.text.SimpleDateFormat
import java.util.*

plugins {
    id("maven-publish")
    id("java")
    id("org.sonarqube") version "7.4.0.8496"
}

val organizationName = "${project.property("organization_name")}"
val projectName = "${project.property("project_name")}"
val versionFromProperty = "${project.property("version")}"
val versionFromEnv: String? = System.getenv("VERSION")

version = versionFromEnv ?: versionFromProperty
group = "${project.property("group")}"
description = "SibDevTools common project API"

val targetJavaVersion = (project.property("jdk_version") as String).toInt()
val javaVersion = JavaVersion.toVersion(targetJavaVersion)

java {
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok:${project.property("lib_lombok_version")}")
    annotationProcessor("org.projectlombok:lombok:${project.property("lib_lombok_version")}")

    implementation("com.fasterxml.jackson.core:jackson-annotations:${project.property("lib_jackson_version")}")
    implementation("jakarta.annotation:jakarta.annotation-api:${project.property("lib_annotation_api_version")}")

}

tasks.withType<JavaCompile>().configureEach {
    // ensure that the encoding is set to UTF-8, no matter what the system default is
    // this fixes some edge cases with special characters not displaying correctly
    // see http://yodaconditions.net/blog/fix-for-java-file-encoding-problems-with-gradle.html
    // If Javadoc is generated, this must be specified in that task too.
    options.encoding = "UTF-8"
    if (targetJavaVersion >= 10 || JavaVersion.current().isJava10Compatible()) {
        options.release = targetJavaVersion
    }
}

java {
    if (JavaVersion.current() < javaVersion) {
        toolchain.languageVersion = JavaLanguageVersion.of(targetJavaVersion)
    }
    withJavadocJar()
    withSourcesJar()
}

tasks.jar {
    from("LICENSE") {
        rename { "${it}_${projectName}" }
    }
    manifest {
        attributes(
            mapOf(
                "Specification-Title" to project.name,
                "Specification-Vendor" to project.property("author"),
                "Specification-Version" to project.version,
                "Specification-Timestamp" to SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(Date()),
                "Timestamp" to System.currentTimeMillis(),
                "Built-On-Java" to "${System.getProperty("java.vm.version")} (${System.getProperty("java.vm.vendor")})"
            )
        )
    }
}

tasks.register("printVersion") {
    doLast {
        println(project.version)
    }
}

sonar {
    properties {
        property("sonar.organization", organizationName)
        property("sonar.projectKey", "${organizationName}_${projectName}")
        property("sonar.projectName", projectName)
        property("sonar.host.url", project.property("sonar.host.url") ?: "https://sonarcloud.io")
        property("sonar.sourceEncoding", "UTF-8")
        property("sonar.java.coveragePlugin", "jacoco")
        property("sonar.scm.disabled", "true")
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            pom {
                packaging = "jar"
                name.set(projectName)
                description.set("SibDevTools common project API")
                url = "https://github.com/${organizationName}/${projectName}"

                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }

                scm {
                    connection.set("scm:git:https://github.com/${organizationName}/${projectName}.git")
                    developerConnection.set("scm:git:ssh://github.com/${organizationName}/${projectName}.git")
                    url.set("https://github.com/${organizationName}/${projectName}")
                }

                developers {
                    developer {
                        id.set("sibmaks")
                        name.set("Maksim Drobyshev")
                        email.set("sibmaks@vk.com")
                    }
                }
            }
        }
    }
}
