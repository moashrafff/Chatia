plugins {
    `kotlin-dsl`
}
repositories{
    google()
    mavenCentral()
    gradlePluginPortal()
}
dependencies {

//    implementation("com.android.tools.build:gradle:8.7.3")
//    api(kotlin("gradle-plugin:1.9.0"))
//    implementation("com.android.tools.build:gradle:8.5.2")
//    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")


    implementation("com.diffplug.spotless:spotless-plugin-gradle:6.22.0")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.23.3")
    implementation("com.github.ben-manes:gradle-versions-plugin:0.51.0")
    implementation("com.squareup:javapoet:1.13.0")
    implementation("org.jetbrains.kotlin:kotlin-serialization:2.2.0")
    implementation("com.google.protobuf:protobuf-gradle-plugin:0.9.4")
}