package com.etpl.demoencora.network

import android.content.Context

interface ApiCallType {
    interface ViewData{
        fun setViewData(str:String,apiConstants: ApiConstants)
    }
    interface call{
        fun onClick(caseConstants: ApiConstants, parameters: Array<String>, context: Context, showProgressBar: Boolean?)
    }
}