package com.etpl.demoencora

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.etpl.demoencora.databinding.ActivityMainBinding
import com.etpl.demoencora.view.HomeFragment
import com.etpl.demoencora.view.SecondFragment

class MainActivity : AppCompatActivity() {
    var isDoubleClick = false
    var commResultCode = 101
    private lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        AppDetails.context = this
        setSupportActionBar(activityMainBinding.mainTool.toolbar)
        initHomeFragment()
        backStackHandel()
    }

    private fun initHomeFragment() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            popAllFragment()
        }
        activityMainBinding.mainTool.title.text = resources.getString(R.string.home_frag)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, HomeFragment())
                .commitAllowingStateLoss()
        Log.e("HomeFragment", "launch")
    }

    private fun popAllFragment() {
        for (i in 0 until supportFragmentManager.backStackEntryCount) {
            supportFragmentManager.popBackStack()
        }
    }

    fun launchFragment(frag: Fragment, tag: String?) {
        supportFragmentManager.beginTransaction()
                .add(R.id.container, frag, tag)
                .addToBackStack(tag)
                .commitAllowingStateLoss()

    }

    private fun backStackHandel() {
        supportFragmentManager.addOnBackStackChangedListener {
            Log.e("BackStack", "call")
            if (supportFragmentManager.backStackEntryCount == 0) {
                activityMainBinding.mainTool.toolbar.title = resources.getString(R.string.home_frag)
            } else {
                var name = supportFragmentManager.getBackStackEntryAt(supportFragmentManager.backStackEntryCount - 1).name
                activityMainBinding.mainTool.toolbar.title = name
            }
        }
    }

    fun startActivityForResult(data: String) {
        var intent = Intent(this, SecondActivityForResult::class.java)
        intent.putExtra("DataFromActivity", data)
        startActivityForResult(intent, commResultCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == commResultCode) {
            when (resultCode) {
                1 -> {
                    initHomeFragment()
                }
                2 -> {
                    launchFragment(SecondFragment(), "Second Fragment")
                }
            }
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            super.onBackPressed()
        } else {
            if (isDoubleClick) {
                super.onBackPressed()
            }
            isDoubleClick = true
            Handler().postDelayed({
                isDoubleClick = false
            }, 2000)
        }
    }
}