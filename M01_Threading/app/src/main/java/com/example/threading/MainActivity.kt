package com.example.threading

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    inner class MyAsyncTask : AsyncTask<Unit, Int, String>() {
        fun primes(): Sequence<Long>{
            var i = 0L
            return sequence{
                generateSequence { i++ }
                    .filter { n -> n > 1 && ((2 until n).none {i -> n % i == 0L}) }
                    .forEach { yield(it)
                    }
            }
        }

        fun progressVisibility(visible: Boolean){
            if (visible){
                progressBar.visibility = View.VISIBLE
            }else{
                progressBar.visibility = View.INVISIBLE
            }
        }

        override fun onPreExecute() {
            txt_primes.visibility = View.INVISIBLE
            progressVisibility(true)

            super.onPreExecute()
        }

        override fun doInBackground(vararg p0: Unit?): String {
            val primeNumbers = primes().take(16000).joinToString(", ")
            return "Primes: $primeNumbers"
        }

        override fun onPostExecute(result: String?) {
            progressVisibility(false)
            txt_primes.text = result
            txt_primes.visibility = View.VISIBLE

            super.onPostExecute(result)
        }

        override fun onCancelled() {
            this.cancel(true)
            super.onCancelled()
        }
    }

    lateinit var task: MyAsyncTask

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_start_generating.setOnClickListener {
            task = MyAsyncTask()
            task.execute()
        }
        btn_cancel.setOnClickListener {
            txt_primes.visibility = View.VISIBLE
            progressBar.visibility = View.INVISIBLE
            task.cancel(true)
        }
    }

    override fun onStop() {
        if(task != null){
            task.cancel(true)
            Log.i("Thread_canceled", "Yes")
        }

        super.onStop()
    }
}
