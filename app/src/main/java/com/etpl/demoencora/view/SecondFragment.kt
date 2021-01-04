package com.etpl.demoencora.view

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.etpl.demoencora.MainActivity
import com.etpl.demoencora.SecondActivityForResult
import com.etpl.demoencora.adapters.HomeAdapter
import com.etpl.demoencora.databinding.SecondFragmentBinding
import com.etpl.demoencora.viewmodel.ArticleViewModel


class SecondFragment : Fragment() {
    var articleViewModel: ArticleViewModel? = null
    var secondFragmentBinding: SecondFragmentBinding? = null
    var homeAdapter: HomeAdapter? = null
    var mPlayer = MediaPlayer()


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        secondFragmentBinding = SecondFragmentBinding.inflate(inflater, container, false)
        return secondFragmentBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        secondFragmentBinding!!.textView.setOnClickListener {
            //(context as MainActivity).launchFragment(DetailsFragment(), resources.getString(R.string.details))
            if(arguments!=null){
                (context as SecondActivityForResult).setResultCode("Data")
            }else{
                (context as MainActivity).startActivityForResult("Data")
            }
        }
    }
    
}
