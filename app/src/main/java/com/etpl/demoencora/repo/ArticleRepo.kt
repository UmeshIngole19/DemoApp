package com.etpl.demoencora.repo

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.etpl.demoencora.model.Docs
import com.etpl.demoencora.model.RoomRes
import com.etpl.demoencora.network.ApiCallType
import com.etpl.demoencora.network.ApiConstants
import com.etpl.demoencora.network.NetworkCall
import com.etpl.demoencora.room.ArticleDao
import com.etpl.demoencora.room.ArticleDataBase
import com.etpl.demoencora.utils.insertAllData
import com.etpl.demoencora.utils.rePopulateDb
import com.etpl.demoencora.view.HomeFragment
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ArticleRepo(val application: Application) : ApiCallType.ViewData {

    var liveDataList: LiveData<List<Docs>>? = null
    var mutableLiveDataList: MutableLiveData<List<Docs>>? = null
    private var articleDao: ArticleDao? = null
    private var api: ApiCallType.call? = null
    var db: ArticleDataBase? = null

    init {
        db = ArticleDataBase.getDatabase(application)
        articleDao = db!!.articleDao()
        liveDataList = articleDao!!.allPosts
        mutableLiveDataList = MutableLiveData()
        api = NetworkCall(this)
        Log.e("mutablelist", "created")
    }


    fun getDataFromApi() {
        api!!.onClick(ApiConstants.GETDNADATA, arrayOf(""), application, true)
    }

    fun getDataFromDb(): LiveData<List<Docs>>? {
        return liveDataList
    }

    override fun setViewData(str: String, apiConstants: ApiConstants) {
        when (apiConstants) {
            ApiConstants.GETDNADATA -> {
                var resp = Gson().fromJson(str, RoomRes::class.java)
                resp.response.docs.forEach {
                    var itr = it.abstractName.listIterator()
                    while (itr.hasNext()) {
                        itr.set(itr.next().replace("\n", ""))
                    }
                }
                HomeFragment.isDataPresent = true
                // set data in database using coroutine
                GlobalScope.launch(Dispatchers.IO) {
                    insertAllData(db, resp.response.docs)
                }
            }
        }
    }
}


