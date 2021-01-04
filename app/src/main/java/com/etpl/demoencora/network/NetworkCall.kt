package com.etpl.demoencora.network

import android.R
import android.content.ContentValues
import android.content.Context
import android.content.Context.WINDOW_SERVICE
import android.graphics.PixelFormat
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.WindowManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import com.etpl.demoencora.network.ApiInterface
import com.etpl.demoencora.AppDetails
import com.etpl.demoencora.MainActivity
import com.etpl.demoencora.databinding.ActivityMainBinding.inflate
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NetworkCall(val repoView : ApiCallType.ViewData) : ApiCallType.call{
   private var progressDialog : CustomProgressDialog?=null
    override fun onClick(
        caseConstants: ApiConstants,
        para: Array<String>,
        context: Context,
        showProgressBar: Boolean?
    ) {
        if (showProgressBar!!) {
            initAndShowProgressBar(context)
        }
        val retrofit = ApiClient.client
        val requestInterface = retrofit.create(ApiInterface::class.java)
        val accessTokenCall: Call<JsonObject>
        when (caseConstants) {
            /* --------------- Umesh --------------------- */
            ApiConstants.GETDNADATA -> {
                accessTokenCall = requestInterface.getDNAData()
                callApiWithAccessToken(accessTokenCall, context, ApiConstants.GETDNADATA)
            }
        }
    }

    private fun callApiWithAccessToken(accessTokenCall: Call<JsonObject>, context: Context, apiConstants: ApiConstants) {
        accessTokenCall.enqueue(object :Callback<JsonObject>{
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (progressDialog != null && progressDialog!!.isShowing)
                    try {
                        if (progressDialog!!.isShowing)
                            progressDialog!!.dismiss()
                    } catch (e: Exception) {
                        Log.e(ContentValues.TAG, "onResponse: $e")
                    }

                if(response.body()!=null){
                   repoView.setViewData(response.body().toString(),apiConstants)
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {

            }
        })
    }

    private fun initAndShowProgressBar(context: Context) {
        progressDialog = CustomProgressDialog(AppDetails.context, "loading");
            progressDialog!!.window!!.setBackgroundDrawable(ColorDrawable(0))
        progressDialog!!.isIndeterminate = true
        progressDialog!!.setCancelable(false)
        try {
            progressDialog!!.show()
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "initAndShowProgressBar: $e")
        }


    }
}