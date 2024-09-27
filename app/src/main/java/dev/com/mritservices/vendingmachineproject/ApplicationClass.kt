package dev.com.mritservices.vendingmachineproject

import android.app.Application
import android.content.Context
import androidx.camera.camera2.Camera2Config
import androidx.camera.core.CameraXConfig
import dagger.hilt.android.HiltAndroidApp



@HiltAndroidApp
class ApplicationClass : Application() , CameraXConfig.Provider {
    companion object {
        var context: Context? = null
        private const val INACTIVITY_TIMEOUT = 2 * 60 * 1000 // 2 minutes in milliseconds
        const val DIRECTORY_NAME = "USBCamera"
    }
    override fun getCameraXConfig(): CameraXConfig {
        return Camera2Config.defaultConfig()
    }
}