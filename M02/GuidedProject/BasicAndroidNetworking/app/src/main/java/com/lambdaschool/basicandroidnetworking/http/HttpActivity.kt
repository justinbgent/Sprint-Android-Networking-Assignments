package com.lambdaschool.basicandroidnetworking.http

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.lambdaschool.basicandroidnetworking.R
import com.lambdaschool.basicandroidnetworking.model.AdviceMsg
import kotlinx.android.synthetic.main.activity_http.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.ref.WeakReference
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.net.UnknownHostException

/**
 * Activity showcases networking calls using HTTPUrlConnection and AsyncTask
 * (less-preferred approach)
 *
 * Also parses JSON result in 2 ways:
 * 1. using JSONObject directly
 * 2. using  Gson library which maps result to "AdviceMsg" model object
 *
 * Example API Result:
 * {
 *    "slip": {
 *       "advice": "Sometimes it's best to ignore other people's advice.",
 *       "slip_id": "17"
 *    }
 * }
 *
 */

val Any.TAG: String
    get() {
        val tag = javaClass.simpleName
        return if (tag.length <= 23) tag else tag.substring(0, 23)
    }

class HttpActivity : AppCompatActivity() {

    companion object {
//        private const val TAG = "HTTP"
        private const val JSON_ERROR = "-ERROR-"
        private const val ADVICE_API_URL = "https://api.adviceslip.com/advice"
        private const val ADVICE_API_TIMEOUT = 5 * 1000 // 5 seconds
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http)

        fetchNetworkAPIButton.setOnClickListener {
            // TODO: Check for network connection. If connected, fetch data, else notify user
            if (isConnected()){
                // Launch AsyncTask for HTTP cal
                AdviceAsyncTask(this).execute()
            }else{
                Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT)
            }
        }
    }

    // TODO: Create a function for checking the network connection
    private fun isConnected(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnected == true
    }

    // TODO: Run code in the background (not on UI thread); ie. for networking calls
    @SuppressLint("NewApi")
    private class AdviceAsyncTask // only retain a weak reference to the activity
    internal constructor(context: HttpActivity) : AsyncTask<Void, Void, String>() {

        private val activityReference: WeakReference<HttpActivity> = WeakReference(context)

        override fun onPreExecute() {
            val activity = activityReference.get()
            if (activity == null || activity.isFinishing) return

            // TODO: Turn progressbar ON
            activity.httpProgressBar.visibility = View.VISIBLE
        }

        override fun doInBackground(vararg v: Void): String? {

            //Open a connection
            var c: HttpURLConnection? = null
            var u: URL? = null

            try {
                u = URL(ADVICE_API_URL)
                c = u.openConnection() as HttpURLConnection

                // Prepare request
                c.apply {
                    setRequestProperty("Content-length", "0")
                    requestMethod = "GET"
                    useCaches = false
                    allowUserInteraction = false
                    connectTimeout = ADVICE_API_TIMEOUT
                    readTimeout = ADVICE_API_TIMEOUT
                }
                val br = BufferedReader(InputStreamReader(c.inputStream))
                val sb = StringBuilder()
                var line = br.readLine()
                while (line != null){
                    sb.append(line + "\n")
                    line = br.readLine()
                }

                br.close()
                return sb.toString()

            } catch (e: UnknownHostException){
                Log.e(TAG, "UnknownHostException")
            }catch (e: MalformedURLException){
                Log.e(TAG, "MalformedURLException")
            }catch (e: IOException){
                Log.e(TAG, "IOException")
            }finally {
                c?.disconnect()
            }



            // Read response
            // Close connection

            // TODO: Define a HttpURLConnection and fetch data
            return JSON_ERROR
        }

        // UI Thread
        override fun onPostExecute(jsonResult: String?) {
            Log.d(TAG, "jsonResult=$jsonResult")

            // get a reference to the activity if it is still there
            val activity = activityReference.get()
            if (activity == null || activity.isFinishing) return

            // TODO: turn progressbar OFF
            activity.httpProgressBar.visibility = View.INVISIBLE

            if (jsonResult.isNullOrEmpty() || jsonResult == JSON_ERROR) {
                Toast.makeText(
                    activity,
                    activity.getString(R.string.invalid_json),
                    Toast.LENGTH_LONG
                ).show()
            }

            // Parse JSON manually
            val advice = parseJsonAdvice(jsonResult)
            Log.d(TAG, "advice=$advice")
            activity.adviceText.text = advice

            // Parse using Gson library
            val adviceGson = parseJsonAdviceGson(jsonResult)
            Log.d(TAG, "adviceGson=$adviceGson")
            Toast.makeText(activity, adviceGson, Toast.LENGTH_SHORT).show()
        }

        // TODO: Write a fun to manually parse a JSON string
        private fun parseJsonAdvice(raw: String?): String {
            return try {
                val adviceJson = JSONObject(raw)
                adviceJson.getJSONObject("slip").getString("advice")
            } catch (t: Throwable){
                ""
            }
        }

        // TODO: Write a fun to parse a JSON string using the Gson Library
        private fun parseJsonAdviceGson(raw: String?): String {
            return try {
                val parser = Gson()
                val adviceMsg = parser.fromJson(raw, AdviceMsg::class.java)
                return adviceMsg.getAdvice() ?: ""
            }catch (t:Throwable){
                ""
            }
        }
    }
}
