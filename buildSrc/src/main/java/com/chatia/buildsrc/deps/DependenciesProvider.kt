package com.chatia.buildsrc.deps


import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.kotlinTest() {
    implementation(Dependencies.KOTLIN_TEST)
    implementation(Dependencies.KOTLIN_TEST_JUNIT)
    testImplementation(Dependencies.JUNIT)
}

fun DependencyHandler.androidCore() {
    implementation(Dependencies.ANDROIDX_CORE_KTX)
}

fun DependencyHandler.androidUi() {
    implementation(Dependencies.ANDROIDX_APPCOMPAT)
    implementation(Dependencies.ANDROIDX_ACTIVITY_COMPOSE)
    implementation(Dependencies.ANDROIDX_LIFECYCLE_VIEWMODEL)
    implementation(Dependencies.ANDROIDX_LIFECYCLE_VIEWMODEL_COMPOSE)
    implementation(Dependencies.ANDROIDX_LIFECYCLE_RUNTIME_COMPOSE)
}

fun DependencyHandler.androidTests() {
    androidTestImplementation(Dependencies.ANDROIDX_TEST_EXT_JUNIT)
    androidTestImplementation(Dependencies.ANDROIDX_ESPRESSO_CORE)
}

fun DependencyHandler.material() {
    implementation(Dependencies.MATERIAL)
}

fun DependencyHandler.ktorServer() {
    implementation(Dependencies.KTOR_SERVER_CORE)
    implementation(Dependencies.KTOR_SERVER_NETTY)
    implementation(Dependencies.KTOR_SERVER_CONTENT_NEGOTIATION)
    implementation(Dependencies.KTOR_SERVER_SWAGGER)
    implementation(Dependencies.KTOR_SERVER_HOST_COMMON)
    implementation(Dependencies.KTOR_SERVER_STATUS_PAGES)
    implementation(Dependencies.KTOR_SERVER_AUTH)
    implementation(Dependencies.KTOR_SERVER_AUTH_JWT)
    implementation(Dependencies.KTOR_SERVER_CONFIG_YAML)
}

fun DependencyHandler.ktorClient() {
    implementation(Dependencies.KTOR_CLIENT_CORE)
    implementation(Dependencies.KTOR_CLIENT_CIO)
    implementation(Dependencies.KTOR_SERIALIZATION_KOTLINX_JSON)
}

fun DependencyHandler.ktorTests() {
    testImplementation(Dependencies.KTOR_SERVER_TEST_HOST)
}

fun DependencyHandler.logging() {
    implementation(Dependencies.LOGBACK_CLASSIC)
}

fun DependencyHandler.exposed() {
    implementation(Dependencies.EXPOSED_CORE)
    implementation(Dependencies.EXPOSED_CORE_V0501)
    implementation(Dependencies.EXPOSED_DAO)
    implementation(Dependencies.EXPOSED_JDBC)
}

fun DependencyHandler.databases() {
    implementation(Dependencies.H2)
    implementation(Dependencies.MYSQL_CONNECTOR_JAVA)
    implementation(Dependencies.POSTGRESQL)
}

fun DependencyHandler.security() {
    implementation(Dependencies.JBCrypt)
}

fun DependencyHandler.koin() {
    implementation(Dependencies.KOIN_KTOR)
    implementation(Dependencies.KOIN_LOGGER_SLF4J)
}
