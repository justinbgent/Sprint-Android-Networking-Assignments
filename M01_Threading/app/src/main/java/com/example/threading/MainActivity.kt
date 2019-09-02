package com.example.threading

import android.os.AsyncTask
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fun publishProgress(progress: Int){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                progressBar.setProgress(progress, true)
            }
        }

        fun primes(length: Int): Sequence<Long>{
            var i = 0L
            return sequence{
                generateSequence { i++ }
                    .filter { n -> n > 1 && ((2 until n).none {i -> n % i == 0L}) }
                    .forEach { yield(it)
                        publishProgress((i/length.toFloat() *100).toInt())}
            }
        }

        fun progressVisibility(visible: Boolean){
            if (visible){
                progressBar.visibility = View.VISIBLE
            }else{
                progressBar.visibility = View.INVISIBLE
            }
        }

        class MyAsyncTask : AsyncTask<Unit, Int, String>() {
            override fun onPreExecute() {
                txt_primes.visibility = View.INVISIBLE
                progressVisibility(true)

                super.onPreExecute()
            }

            override fun doInBackground(vararg p0: Unit?): String {
                val primeNumbers = primes(16000).take(16000).joinToString(", ")
                return "Primes: $primeNumbers"
            }

            override fun onPostExecute(result: String?) {
                progressVisibility(false)
                txt_primes.text = result
                txt_primes.visibility = View.VISIBLE

                super.onPostExecute(result)
            }

            override fun onCancelled(result: String?) {
                super.onCancelled(result)
            }

            override fun onCancelled() {
                super.onCancelled()
            }
        }

        var task = MyAsyncTask()
        task.execute(txt_primes.text.toString() as Unit)
        task.cancel(true)

    }



    override fun onStop() {
        super.onStop()
    }
}
