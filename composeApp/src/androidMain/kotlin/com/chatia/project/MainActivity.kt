package com.chatia.project

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.chatia.presentation.permission.PermissionBridge
import com.chatia.presentation.permission.PermissionResultCallback
import com.chatia.presentation.permission.PermissionState
import com.chatia.presentation.permission.PermissionsBridgeListener
import org.koin.core.context.GlobalContext

class MainActivity : ComponentActivity(), PermissionsBridgeListener {

    private var permissionResultCallback: PermissionResultCallback? = null

    private val requestPermissionLauncher: ActivityResultLauncher<Array<String>> =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->

            if (result.all { it.value }) {
                permissionResultCallback?.onPermissionGranted()
            } else {

                val states = result.mapValues { (permission, granted) ->
                    if (granted) {
                        PermissionState.GRANTED
                    } else {
                        val rationale = shouldShowRequestPermissionRationale(permission)

                        if (rationale) {
                            PermissionState.SHOULD_SHOW_RATIONALE
                        } else {
                            PermissionState.PERMANENTLY_DENIED
                        }
                    }
                }

                val deniedOnly = states.filterValues { it != PermissionState.GRANTED }

                permissionResultCallback?.onPermissionDenied(deniedOnly)
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GlobalContext.get().get<PermissionBridge>().setListener(this)

        setContent {
            App()
        }
    }
    fun Context.openAppSettings() {
        val intent = Intent(
            //Settings.
            ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", packageName, null)
        ).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(intent)
    }


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun requestAppPermissions(callback: PermissionResultCallback) {
        val permissions = mutableListOf(
            Manifest.permission.READ_CALENDAR,
            Manifest.permission.ACCESS_FINE_LOCATION,
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissions.add(Manifest.permission.POST_NOTIFICATIONS)
            permissions.add(Manifest.permission.READ_MEDIA_IMAGES)
        } else {
             permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        when {
            permissions.all {
                ContextCompat.checkSelfPermission(
                    this,
                    it
                ) == PackageManager.PERMISSION_GRANTED
            }

                -> {
                callback.onPermissionGranted()
            }

            else -> {
                permissionResultCallback = callback
                requestPermissionLauncher.launch(permissions.toTypedArray())
            }
        }

    }

    override fun areAppPermissionsGranted(): Boolean {
        val permissions = mutableListOf(
            Manifest.permission.READ_CALENDAR,
            Manifest.permission.ACCESS_FINE_LOCATION,
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissions.add(Manifest.permission.POST_NOTIFICATIONS)
            permissions.add(Manifest.permission.READ_MEDIA_IMAGES)
        } else {
            permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        return permissions.all {
            ContextCompat.checkSelfPermission(
                this,
                it
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    override fun openSettings() {
        val intent = Intent(
            ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", packageName, null)
        ).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(intent)
    }
}