package com.example.servicesandbroadcasts

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class FileDownloadService: Service() {
    companion object{
        const val FILE_DOWNLOADED_ACTION = "Download_http://getwallpapers.com/wallpaper/full/6/a/5/182229.jpg"
        const val DOWNLOADED_IMAGE = "KEY"
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Thread(Runnable {
            val bitmap = NetworkAdapter.getBitmapFromUrl("http://getwallpapers.com/wallpaper/full/6/a/5/182229.jpg", 1920, 1080)

            val intent = Intent(FILE_DOWNLOADED_ACTION).apply {
                putExtra(DOWNLOADED_IMAGE, bitmap)
            }
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
            stopSelf()
        }).start()

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

}
