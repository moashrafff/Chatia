//package com.chatia.buildsrc.build
//
//
//import com.android.build.api.dsl.ApplicationBuildType
//import com.android.build.api.dsl.LibraryBuildType
//import org.gradle.api.NamedDomainObjectContainer
//import org.gradle.api.Project
//
//sealed class BuildCreator(val name: String) {
//
//    abstract fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationBuildType>): ApplicationBuildType
//
//    abstract fun createLibrary(namedDomainObjectContainer: NamedDomainObjectContainer<LibraryBuildType>): LibraryBuildType
//
//    class Debug(private val project: Project) : BuildCreator(BuildTypes.DEBUG) {
//        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationBuildType>): ApplicationBuildType =
//            namedDomainObjectContainer.getByName(name) {
//                isMinifyEnabled = Build.DEBUG.isMinifyEnabled
//                isShrinkResources = Build.DEBUG.isShrinkResources
//                enableAndroidTestCoverage = Build.DEBUG.isTestCoverageEnabled
//                isDebuggable = Build.DEBUG.isDebuggable
//                applicationIdSuffix = Build.DEBUG.applicationIdSuffix
//                versionNameSuffix = Build.DEBUG.versionNameSuffix
//            }
//
//        override fun createLibrary(namedDomainObjectContainer: NamedDomainObjectContainer<LibraryBuildType>): LibraryBuildType =
//            namedDomainObjectContainer.getByName(name) {
//                isMinifyEnabled = Build.DEBUG.isMinifyEnabled
//                isShrinkResources = Build.DEBUG.isShrinkResources
//                enableAndroidTestCoverage = Build.DEBUG.isTestCoverageEnabled
//            }
//    }
//
//    class QaRelease(private val project: Project) : BuildCreator(BuildTypes.QA_RELEASE) {
//        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationBuildType>): ApplicationBuildType =
//            namedDomainObjectContainer.create(name) {
//                isMinifyEnabled = Build.QA_RELEASE.isMinifyEnabled
//                isShrinkResources = Build.QA_RELEASE.isShrinkResources
//                enableAndroidTestCoverage = Build.QA_RELEASE.isTestCoverageEnabled
//                isDebuggable = Build.QA_RELEASE.isDebuggable
//                applicationIdSuffix = Build.QA_RELEASE.applicationIdSuffix
//                versionNameSuffix = Build.QA_RELEASE.versionNameSuffix
//            }
//
//        override fun createLibrary(namedDomainObjectContainer: NamedDomainObjectContainer<LibraryBuildType>): LibraryBuildType =
//            namedDomainObjectContainer.create(name) {
//                isMinifyEnabled = Build.QA_RELEASE.isMinifyEnabled
//                enableAndroidTestCoverage = Build.QA_RELEASE.isTestCoverageEnabled
//            }
//    }
//
//    class Release(private val project: Project) : BuildCreator(BuildTypes.RELEASE) {
//        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationBuildType>): ApplicationBuildType =
//            namedDomainObjectContainer.getByName(name) {
////                isMinifyEnabled = Build.RELEASE.isMinifyEnabled
////                isShrinkResources = Build.RELEASE.isShrinkResources
//                enableAndroidTestCoverage = Build.RELEASE.isTestCoverageEnabled
//                isDebuggable = Build.RELEASE.isDebuggable
//            }
//
//        override fun createLibrary(namedDomainObjectContainer: NamedDomainObjectContainer<LibraryBuildType>): LibraryBuildType =
//            namedDomainObjectContainer.getByName(name) {
////                isMinifyEnabled = Build.RELEASE.isMinifyEnabled
//                enableAndroidTestCoverage = Build.RELEASE.isTestCoverageEnabled
//            }
//    }
//}