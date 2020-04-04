plugins {
    id("org.jetbrains.kotlin.js") version "1.3.71"
}

group = "org.olafneumann"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-js"))
    testImplementation(kotlin("test-js"))
}

kotlin.target.browser { }

tasks.register<Copy>("createGithubPages") {
    from("$buildDir/distributions", "src/github/resources")
    include("*.html", "*.js", "*.css", "CNAME")
    into("$buildDir/gh-pages")
}

tasks.register("cleanBuildGithub") {
    dependsOn("clean")
    dependsOn("build")
    dependsOn("createGithubPages")
    tasks.findByName("build")?.mustRunAfter("clean")
    tasks.findByName("createGithubPages")?.mustRunAfter("build")
}
