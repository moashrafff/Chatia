package com.chatia.buildsrc.deps

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.file.ConfigurableFileCollection

fun DependencyHandler.implementation(dependency: String) {
    add("implementation", dependency)
}

fun DependencyHandler.testImplementation(dependency: String) {
    add("testImplementation", dependency)
}

fun DependencyHandler.androidTestImplementation(dependency: String) {
    add("androidTestImplementation", dependency)
}

fun DependencyHandler.debugImplementation(dependency: String) {
    add("debugImplementation", dependency)
}

fun DependencyHandler.releaseImplementation(dependency: String) {
    add("releaseImplementation", dependency)
}

fun DependencyHandler.kapt(dependency: String) {
    add("kapt", dependency)
}

fun DependencyHandler.moduleImplementation(dependency: Dependency) {
    add("implementation", dependency)
}

fun DependencyHandler.moduleCompileOnly(dependency: ConfigurableFileCollection) {
    add("compileOnly", dependency)
}

fun DependencyHandler.moduleCompileOnly(dependency: ProjectDependency) {
    add("compileOnly", dependency)
}

fun DependencyHandler.moduleCompileOnly(dependency: String) {
    add("compileOnly", dependency)
}
