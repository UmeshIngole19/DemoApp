package com.etpl.demoencora.room

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.etpl.demoencora.model.Docs
import com.etpl.demoencora.utils.Converters
import com.etpl.demoencora.utils.rePopulateDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = [Docs::class], version = 1,exportSchema = false)
@TypeConverters(Converters::class)
abstract class ArticleDataBase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao


    companion object {
        private var INSTANCE: ArticleDataBase? = null
        private const val DB_NAME = "abc.db"

        fun getDatabase(context: Context): ArticleDataBase {
            if (INSTANCE == null) {
                synchronized(ArticleDataBase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext, ArticleDataBase::class.java, DB_NAME)
                                //.allowMainThreadQueries() // Uncomment if you don't want to use RxJava or coroutines just yet (blocks UI thread)
                                .addCallback(object : Callback() {
                                    override fun onCreate(db: SupportSQLiteDatabase) {
                                        super.onCreate(db)
                                        Log.d("MoviesDatabase", "populating with data...")
                                        GlobalScope.launch(Dispatchers.IO) { rePopulateDb(INSTANCE) }
                                    }
                                }).build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}