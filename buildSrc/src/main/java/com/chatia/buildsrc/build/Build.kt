package com.chatia.buildsrc.build

sealed class Build {
    open val isDebuggable = false
    open val isMinifyEnabled = false
    open val isShrinkResources = false
    open val isTestCoverageEnabled = false

    open val applicationIdSuffix = ""
    open val versionNameSuffix = ""

    object DEBUG : Build() {
        override val isDebuggable = true
        override val isMinifyEnabled = false
        override val isShrinkResources = false
        override val isTestCoverageEnabled = true
        override val applicationIdSuffix = ".debug"
        override val versionNameSuffix = "-DEBUG"
    }

    object QA_RELEASE : Build() {
        override val isDebuggable = false
        override val isMinifyEnabled = true
        override val isShrinkResources = true
        override val isTestCoverageEnabled = false
        override val applicationIdSuffix = ".qaRelease"
        override val versionNameSuffix = "-QA"
    }

    object RELEASE : Build() {
        override val isDebuggable = false
        override val isMinifyEnabled = true
        override val isShrinkResources = true
        override val isTestCoverageEnabled = false
        override val applicationIdSuffix = ".release"
        override val versionNameSuffix = "-RELEASE"
    }

}