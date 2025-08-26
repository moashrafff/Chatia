package com.chatia.buildsrc.deps

object Dependencies {
    // Kotlin + Test
    const val KOTLIN_TEST = "org.jetbrains.kotlin:kotlin-test:${Versions.KOTLIN}"
    const val KOTLIN_TEST_JUNIT = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.KOTLIN}"
    const val JUNIT = "junit:junit:${Versions.JUNIT}"

    // AndroidX
    const val ANDROIDX_CORE_KTX = "androidx.core:core-ktx:${Versions.ANDROIDX_CORE}"
    const val ANDROIDX_TEST_EXT_JUNIT = "androidx.test.ext:junit:${Versions.ANDROIDX_TEST_EXT}"
    const val ANDROIDX_ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ANDROIDX_ESPRESSO}"
    const val ANDROIDX_APPCOMPAT = "androidx.appcompat:appcompat:${Versions.ANDROIDX_APPCOMPAT}"
    const val ANDROIDX_ACTIVITY_COMPOSE = "androidx.activity:activity-compose:${Versions.ANDROIDX_ACTIVITY}"
    const val ANDROIDX_LIFECYCLE_VIEWMODEL = "org.jetbrains.androidx.lifecycle:lifecycle-viewmodel:${Versions.ANDROIDX_LIFECYCLE}"
    const val ANDROIDX_LIFECYCLE_VIEWMODEL_COMPOSE = "org.jetbrains.androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.ANDROIDX_LIFECYCLE}"
    const val ANDROIDX_LIFECYCLE_RUNTIME_COMPOSE = "org.jetbrains.androidx.lifecycle:lifecycle-runtime-compose:${Versions.ANDROIDX_LIFECYCLE}"

    // Material
    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"

    // Ktor
    const val KTOR_SERVER_CORE = "io.ktor:ktor-server-core-jvm:${Versions.KTOR}"
    const val KTOR_SERVER_NETTY = "io.ktor:ktor-server-netty-jvm:${Versions.KTOR}"
    const val KTOR_SERVER_TEST_HOST = "io.ktor:ktor-server-test-host-jvm:${Versions.KTOR}"
    const val KTOR_CLIENT_CORE = "io.ktor:ktor-client-core:${Versions.KTOR}"
    const val KTOR_CLIENT_CIO = "io.ktor:ktor-client-cio:${Versions.KTOR}"
    const val KTOR_SERVER_SWAGGER = "io.ktor:ktor-server-swagger:${Versions.KTOR}"
    const val KTOR_SERVER_CONTENT_NEGOTIATION = "io.ktor:ktor-server-content-negotiation:${Versions.KTOR}"
    const val KTOR_SERIALIZATION_KOTLINX_JSON = "io.ktor:ktor-serialization-kotlinx-json:${Versions.KTOR}"
    const val KTOR_SERVER_HOST_COMMON = "io.ktor:ktor-server-host-common:${Versions.KTOR}"
    const val KTOR_SERVER_STATUS_PAGES = "io.ktor:ktor-server-status-pages:${Versions.KTOR}"
    const val KTOR_SERVER_AUTH = "io.ktor:ktor-server-auth:${Versions.KTOR}"
    const val KTOR_SERVER_AUTH_JWT = "io.ktor:ktor-server-auth-jwt:${Versions.KTOR}"
    const val KTOR_SERVER_CONFIG_YAML = "io.ktor:ktor-server-config-yaml:${Versions.KTOR}"

    // Logging
    const val LOGBACK_CLASSIC = "ch.qos.logback:logback-classic:${Versions.LOGBACK}"

    // Exposed
    const val EXPOSED_CORE = "org.jetbrains.exposed:exposed-core:${Versions.EXPOSED_CORE}"
    const val EXPOSED_CORE_V0501 = "org.jetbrains.exposed:exposed-core:${Versions.EXPOSED_CORE_VERSION}"
    const val EXPOSED_DAO = "org.jetbrains.exposed:exposed-dao:${Versions.EXPOSED_DAO}"
    const val EXPOSED_JDBC = "org.jetbrains.exposed:exposed-jdbc:${Versions.EXPOSED_JDBC}"

    // DBs
    const val H2 = "com.h2database:h2:${Versions.H2}"
    const val MYSQL_CONNECTOR_JAVA = "mysql:mysql-connector-java:${Versions.MYSQL_CONNECTOR_JAVA}"
    const val POSTGRESQL = "org.postgresql:postgresql:${Versions.POSTGRESQL}"

    // Security
    const val JBCrypt = "org.mindrot:jbcrypt:${Versions.JBCrypt}"

    // Koin
    const val KOIN_KTOR = "io.insert-koin:koin-ktor:${Versions.KOIN_KTOR}"
    const val KOIN_LOGGER_SLF4J = "io.insert-koin:koin-logger-slf4j:${Versions.KOIN_KTOR}"
}
