package com.etpl.demoencora

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log


class MyApplication : Application(), Application.ActivityLifecycleCallbacks{

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(this)
        Log.e("Applications onCreate","call")

    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        activity!!.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        AppDetails.activity = activity;
        AppDetails.context = this
    }

    override fun onActivityPaused(activity: Activity?) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivityResumed(activity: Activity?) {
        Log.e("Applications onResume","call")
        //registerReceiver(broadcastReceiver, IntentFilter(AppConstant.ACTION_SEND_MESSAGE))
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivityStarted(activity: Activity?) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivityDestroyed(activity: Activity?) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
        Log.e("Applications saveInstan","call")

//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivityStopped(activity: Activity?) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    /*val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if(intent.action == AppConstant.ACTION_SEND_MESSAGE) {
                java.lang.Double.valueOf(intent.getStringExtra("latitude"))
                 java.lang.Double.valueOf(intent.getStringExtra("longitude"))

                var i = Intent(this@MyApplication,BaseDrawerActivity::class.java)
                i.setAction("test")
                sendBroadcast(i)

                Log.e("Applications","running")
            }
        }
    }*/
}