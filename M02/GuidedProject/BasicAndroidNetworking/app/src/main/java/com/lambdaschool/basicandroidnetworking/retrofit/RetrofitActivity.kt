package com.lambdaschool.basicandroidnetworking.retrofit

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lambdaschool.basicandroidnetworking.R
import com.lambdaschool.basicandroidnetworking.model.AdviceMsg
import kotlinx.android.synthetic.main.activity_retrofit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: declare this activity handles Retrofit callbacks
class RetrofitActivity : AppCompatActivity(), Callback<AdviceMsg> {
    override fun onFailure(call: Call<AdviceMsg>, t: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        Toast.makeText(this, "The call failed", Toast.LENGTH_SHORT).show()
    }

    override fun onResponse(call: Call<AdviceMsg>, response: Response<AdviceMsg>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        adviceTextRetrofit?.text = response.body()?.getAdvice() ?: "The call failed"
    }

    companion object {
        private const val TAG = "RETROFIT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        fetchNetworkAPIRetrofitButton.setOnClickListener {

            // TODO: Get advice without logging
            val retriever = AdviceRetriever()
            retriever.getRandomAdvice().enqueue(this)

        }

        fetchNetworkAPIOkHttpButton.setOnClickListener {
            //TODO: Get advice with logging
            val retriever = AdviceRetriever()
            retriever.getRandomAdviceWithOkHttp().enqueue(this)
        }
    }

    // TODO: Define callback for Retrofit onResponse

    // TODO: Define callback for Retrofit onFailure
}
