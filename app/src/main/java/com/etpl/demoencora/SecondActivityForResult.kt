package com.etpl.demoencora

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.etpl.demoencora.databinding.ActivityMainBinding
import com.etpl.demoencora.databinding.SecondActivityBinding
import com.etpl.demoencora.view.HomeFragment
import com.etpl.demoencora.view.SecondFragment
import com.etpl.demoencora.viewmodel.ArticleViewModel
import java.util.*

class SecondActivityForResult : AppCompatActivity() {
    private lateinit var secondActivityBinding: SecondActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        secondActivityBinding = DataBindingUtil.setContentView(this, R.layout.second_activity)
        setSupportActionBar(secondActivityBinding.mainTool.toolbar)

        supportFragmentManager.addOnBackStackChangedListener {
            Log.e("BackStack", "call")
            if (supportFragmentManager.backStackEntryCount == 0) {
                secondActivityBinding.mainTool.toolbar.title = resources.getString(R.string.second_frag_from_2)
            } else {
                var name = supportFragmentManager.getBackStackEntryAt(supportFragmentManager.backStackEntryCount - 1).name
                secondActivityBinding.mainTool.toolbar.title = name
            }
        }
        initialFragment()
    }

    fun setResultCode(data: String) {
        var intent = Intent(this, MainActivity::class.java)
        intent.putExtra("DataFrom2Activity", data)
        setResult(2, intent)
    }

    private fun initialFragment() {
        var farg = SecondFragment()
        var bundle = Bundle()
        bundle.putString("From", "2Activity")
        farg.arguments = bundle
        supportFragmentManager.beginTransaction()
                .replace(R.id.secondActivityContainer, farg, "From SecondActivity")
                .commitAllowingStateLoss()
    }


}