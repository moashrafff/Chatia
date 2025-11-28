package com.chatia.project

import com.chatia.presentation.permission.PermissionResultCallback
import com.chatia.presentation.permission.PermissionState
import com.chatia.presentation.permission.PermissionsBridgeListener
import platform.AVFoundation.AVAuthorizationStatusAuthorized
import platform.AVFoundation.AVAuthorizationStatusDenied
import platform.AVFoundation.AVAuthorizationStatusNotDetermined
import platform.AVFoundation.AVCaptureDevice
import platform.AVFoundation.AVMediaTypeVideo
import platform.AVFoundation.authorizationStatusForMediaType
import platform.AVFoundation.requestAccessForMediaType
import platform.CoreLocation.CLLocationManager
import platform.CoreLocation.kCLAuthorizationStatusAuthorizedAlways
import platform.CoreLocation.kCLAuthorizationStatusAuthorizedWhenInUse
import platform.CoreLocation.kCLAuthorizationStatusDenied
import platform.CoreLocation.kCLAuthorizationStatusNotDetermined
import platform.EventKit.EKAuthorizationStatusAuthorized
import platform.EventKit.EKAuthorizationStatusDenied
import platform.EventKit.EKAuthorizationStatusNotDetermined
import platform.EventKit.EKEntityType
import platform.EventKit.EKEventStore
import platform.Foundation.NSURL
import platform.Photos.PHAuthorizationStatusAuthorized
import platform.Photos.PHAuthorizationStatusDenied
import platform.Photos.PHAuthorizationStatusNotDetermined
import platform.Photos.PHPhotoLibrary
import platform.UIKit.UIApplication
import platform.UIKit.UIApplicationOpenSettingsURLString
import platform.UserNotifications.UNAuthorizationOptionAlert
import platform.UserNotifications.UNAuthorizationOptionBadge
import platform.UserNotifications.UNAuthorizationOptionSound
import platform.UserNotifications.UNUserNotificationCenter
import platform.darwin.dispatch_after
import platform.darwin.dispatch_get_main_queue
import platform.darwin.dispatch_time
import platform.darwin.DISPATCH_TIME_NOW

class IosPermissionHandler : PermissionsBridgeListener {
    private val locationManager = CLLocationManager()
    
    // Centralized permission names to avoid duplication
    private companion object {
        const val PERMISSION_CAMERA = "Camera"
        const val PERMISSION_GALLERY = "Gallery"
        const val PERMISSION_LOCATION = "Location"
        const val PERMISSION_NOTIFICATION = "Notification"
        const val PERMISSION_CALENDAR = "Calendar"
        
        // Small delay for location permission (100ms) to avoid race condition
        const val LOCATION_CHECK_DELAY_MS = 100L
    }
    
    private data class PermissionResults(
        var cameraGranted: Boolean? = null,
        var galleryGranted: Boolean? = null,
        var locationGranted: Boolean? = null,
        var notificationGranted: Boolean? = null,
        var calendarGranted: Boolean? = null
    ) {
        fun isComplete() = cameraGranted != null && galleryGranted != null && 
                          locationGranted != null && notificationGranted != null && 
                          calendarGranted != null
        
        fun allGranted() = cameraGranted == true && galleryGranted == true && 
                          locationGranted == true && notificationGranted == true && 
                          calendarGranted == true
    }

    override fun requestAppPermissions(callback: PermissionResultCallback) {
        val results = PermissionResults()
        
        // Request Camera
        requestCameraPermission { granted ->
            results.cameraGranted = granted
            checkAndCallback(results, callback)
        }
        
        // Request Gallery
        requestGalleryPermission { granted ->
            results.galleryGranted = granted
            checkAndCallback(results, callback)
        }
        
        // Request Location (with delay for status check)
        requestLocationPermission { granted ->
            results.locationGranted = granted
            checkAndCallback(results, callback)
        }
        
        // Request Notifications
        requestNotificationPermission { granted ->
            results.notificationGranted = granted
            checkAndCallback(results, callback)
        }
        
        // Request Calendar
        requestCalendarPermission { granted ->
            results.calendarGranted = granted
            checkAndCallback(results, callback)
        }
    }
    
    private fun requestCameraPermission(completion: (Boolean) -> Unit) {
        AVCaptureDevice.requestAccessForMediaType(AVMediaTypeVideo, completion)
    }
    
    private fun requestGalleryPermission(completion: (Boolean) -> Unit) {
        PHPhotoLibrary.requestAuthorization { status ->
            completion(status == PHAuthorizationStatusAuthorized)
        }
    }
    
    private fun requestLocationPermission(completion: (Boolean) -> Unit) {
        locationManager.requestWhenInUseAuthorization()
        
        // Add delay to avoid race condition - wait for user response
        val delayNanos = LOCATION_CHECK_DELAY_MS * 1_000_000L
        dispatch_after(
            dispatch_time(DISPATCH_TIME_NOW, delayNanos),
            dispatch_get_main_queue()
        ) {
            val status = CLLocationManager.authorizationStatus()
            val granted = status == kCLAuthorizationStatusAuthorizedWhenInUse || 
                         status == kCLAuthorizationStatusAuthorizedAlways
            completion(granted)
        }
    }
    
    private fun requestNotificationPermission(completion: (Boolean) -> Unit) {
        val center = UNUserNotificationCenter.currentNotificationCenter()
        center.requestAuthorizationWithOptions(
            UNAuthorizationOptionAlert or UNAuthorizationOptionSound or UNAuthorizationOptionBadge
        ) { granted, _ ->
            completion(granted)
        }
    }
    
    private fun requestCalendarPermission(completion: (Boolean) -> Unit) {
        val eventStore = EKEventStore()
        eventStore.requestAccessToEntityType(EKEntityType.EKEntityTypeEvent) { granted, _ ->
            completion(granted)
        }
    }
    
    private fun checkAndCallback(results: PermissionResults, callback: PermissionResultCallback) {
        if (!results.isComplete()) return
        
        if (results.allGranted()) {
            callback.onPermissionGranted()
        } else {
            val deniedMap = mutableMapOf<String, PermissionState>()
            
            if (results.cameraGranted == false) {
                deniedMap[PERMISSION_CAMERA] = getCameraPermissionState()
            }
            if (results.galleryGranted == false) {
                deniedMap[PERMISSION_GALLERY] = getGalleryPermissionState()
            }
            if (results.locationGranted == false) {
                deniedMap[PERMISSION_LOCATION] = getLocationPermissionState()
            }
            if (results.notificationGranted == false) {
                deniedMap[PERMISSION_NOTIFICATION] = PermissionState.PERMANENTLY_DENIED
            }
            if (results.calendarGranted == false) {
                deniedMap[PERMISSION_CALENDAR] = getCalendarPermissionState()
            }
            
            callback.onPermissionDenied(deniedMap)
        }
    }

    override fun areAppPermissionsGranted(): Boolean {
        val cameraStatus = AVCaptureDevice.authorizationStatusForMediaType(AVMediaTypeVideo)
        val galleryStatus = PHPhotoLibrary.authorizationStatus()
        val locationStatus = CLLocationManager.authorizationStatus()
        val calendarStatus = EKEventStore.authorizationStatusForEntityType(EKEntityType.EKEntityTypeEvent)
        
        // Note: Notification status check requires async callback in iOS, 
        // so we only check synchronously available permissions here.
        // Notifications are verified during the request flow.
        
        return cameraStatus == AVAuthorizationStatusAuthorized &&
               galleryStatus == PHAuthorizationStatusAuthorized &&
               (locationStatus == kCLAuthorizationStatusAuthorizedWhenInUse || 
                locationStatus == kCLAuthorizationStatusAuthorizedAlways) &&
               calendarStatus == EKAuthorizationStatusAuthorized
    }

    override fun openSettings() {
        val url = NSURL.URLWithString(UIApplicationOpenSettingsURLString)
        url?.let {
            UIApplication.sharedApplication.openURL(it, mapOf<Any?, Any>(), null)
        }
    }
    
    // Fixed: Use iOS enum constants instead of magic numbers
    private fun getCameraPermissionState(): PermissionState {
        val status = AVCaptureDevice.authorizationStatusForMediaType(AVMediaTypeVideo)
        return mapAVAuthorizationStatus(status)
    }
    
    private fun getGalleryPermissionState(): PermissionState {
        val status = PHPhotoLibrary.authorizationStatus()
        return mapPHAuthorizationStatus(status)
    }
    
    private fun getLocationPermissionState(): PermissionState {
        val status = CLLocationManager.authorizationStatus()
        return mapCLAuthorizationStatus(status)
    }
    
    private fun getCalendarPermissionState(): PermissionState {
        val status = EKEventStore.authorizationStatusForEntityType(EKEntityType.EKEntityTypeEvent)
        return mapEKAuthorizationStatus(status)
    }
    
    private fun mapAVAuthorizationStatus(status: Long): PermissionState {
        return when (status.toInt()) {
            AVAuthorizationStatusNotDetermined.toInt() -> PermissionState.SHOULD_SHOW_RATIONALE
            AVAuthorizationStatusDenied.toInt() -> PermissionState.PERMANENTLY_DENIED
            AVAuthorizationStatusAuthorized.toInt() -> PermissionState.GRANTED
            else -> PermissionState.PERMANENTLY_DENIED
        }
    }
    
    private fun mapPHAuthorizationStatus(status: Long): PermissionState {
        return when (status.toInt()) {
            PHAuthorizationStatusNotDetermined.toInt() -> PermissionState.SHOULD_SHOW_RATIONALE
            PHAuthorizationStatusDenied.toInt() -> PermissionState.PERMANENTLY_DENIED
            PHAuthorizationStatusAuthorized.toInt() -> PermissionState.GRANTED
            else -> PermissionState.PERMANENTLY_DENIED
        }
    }
    
    private fun mapCLAuthorizationStatus(status: Int): PermissionState {
        return when (status) {
            kCLAuthorizationStatusNotDetermined -> PermissionState.SHOULD_SHOW_RATIONALE
            kCLAuthorizationStatusDenied -> PermissionState.PERMANENTLY_DENIED
            kCLAuthorizationStatusAuthorizedWhenInUse, 
            kCLAuthorizationStatusAuthorizedAlways -> PermissionState.GRANTED
            else -> PermissionState.PERMANENTLY_DENIED
        }
    }
    
    private fun mapEKAuthorizationStatus(status: Long): PermissionState {
        return when (status.toInt()) {
            EKAuthorizationStatusNotDetermined.toInt() -> PermissionState.SHOULD_SHOW_RATIONALE
            EKAuthorizationStatusDenied.toInt() -> PermissionState.PERMANENTLY_DENIED
            EKAuthorizationStatusAuthorized.toInt() -> PermissionState.GRANTED
            else -> PermissionState.PERMANENTLY_DENIED
        }
    }
}
