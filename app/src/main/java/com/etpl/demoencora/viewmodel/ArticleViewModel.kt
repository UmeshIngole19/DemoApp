package com.etpl.demoencora.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.etpl.demoencora.model.Docs
import com.etpl.demoencora.repo.ArticleRepo

class ArticleViewModel(application: Application):AndroidViewModel(application){
    var articleRepo: ArticleRepo?=null
    var liveData:LiveData<List<Docs>>?=null

    init {
        articleRepo = ArticleRepo(application)
        liveData = MutableLiveData<List<Docs>>()
    }

     fun getDnaData(){
            articleRepo!!.getDataFromApi()
         Log.e("getDataCall","Call")
    }

    fun getDnaDataFromDb():LiveData<List<Docs>>{
       liveData = articleRepo!!.getDataFromDb()
        return liveData!!
    }

}