package com.lambdaschool.serviceimagedownloader

import android.app.IntentService
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.support.v4.content.LocalBroadcastManager
import android.util.Log
import java.lang.UnsupportedOperationException

// TODO: S04M04-1 create new service
class LargeImageDownloadService: IntentService("LargeImageDownload"){
    override fun onHandleIntent(intent: Intent?) {
        Thread(Runnable() {
            // TODO: S04M04-3 Add network call
            val width = intent?.getIntExtra(BITMAP_WIDTH, 0) ?: 0
            val height = intent?.getIntExtra(BITMAP_HEIGHT, 0) ?: 0
            val bitmap = NetworkAdapter.getBitmapFromUrl("https://i.imgur.com/HaSmgGn.jpg", width, height)

            val intent = Intent(FILE_DOWNLOADED).apply {
                putExtra(DOWNLOADED_IMAGE, bitmap)
            }
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent)

        }).start()
    }

//    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        Log.i("CycleLife", "it StartCommand")
//
//        Thread(Runnable() {
//            // TODO: S04M04-3 Add network call
//            val width = intent?.getIntExtra(BITMAP_WIDTH, 0) ?: 0
//            val height = intent?.getIntExtra(BITMAP_HEIGHT, 0) ?: 0
//            val bitmap = NetworkAdapter.getBitmapFromUrl("https://i.imgur.com/HaSmgGn.jpg", width, height)
//
//            val intent = Intent(FILE_DOWNLOADED).apply {
//                putExtra(DOWNLOADED_IMAGE, bitmap)
//            }
//            LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
//            stopSelf()
//        }).start()
//
//
//        return super.onStartCommand(intent, flags, startId)
//    }
//
//    override fun onCreate() {
//        Log.i("CycleLife", "it onCreate")
//        super.onCreate()
//    }
//
//    override fun onDestroy() {
//        Log.i("CycleLife", "it Destroyed")
//        super.onDestroy()
//    }
//
//    override fun onBind(p0: Intent?): IBinder? {
////        return null
//        throw UnsupportedOperationException()
//    }

    companion object{
        const val FILE_DOWNLOADED = "com.lambdashcool.serviceimagedownloader.FILE_DOWNLOADED"
        const val DOWNLOADED_IMAGE = ""
        const val BITMAP_HEIGHT = "HEIGHT"
        const val BITMAP_WIDTH = "WIDTH"
    }
}

// TODO: S04M04-2 Override onStartCommand

