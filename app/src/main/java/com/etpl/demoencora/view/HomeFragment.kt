package com.etpl.demoencora.view

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.etpl.demoencora.MainActivity
import com.etpl.demoencora.R
import com.etpl.demoencora.adapters.HomeAdapter
import com.etpl.demoencora.databinding.FragmentHomeBinding
import com.etpl.demoencora.viewmodel.ArticleViewModel
import java.io.IOException


class HomeFragment : Fragment() {
    var articleViewModel:ArticleViewModel?=null
    var fragmentHomeBinding:FragmentHomeBinding?=null
    var homeAdapter:HomeAdapter?=null
    var mPlayer = MediaPlayer()


    companion object HomeFragment{
       var isDataPresent = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater,container,false)
        return fragmentHomeBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        fragmentHomeBinding!!.button.setOnClickListener {
            (context as MainActivity).launchFragment(SecondFragment(), resources.getString(R.string.second_frag_from_1))
        }
        fragmentHomeBinding!!.button2.setOnClickListener {
            //(context as MainActivity).launchFragment(DetailsFragment(), resources.getString(R.string.details))
            // call activity result from frag1 of main activity
            (context as MainActivity).startActivityForResult("Data")
           /* var intent = Intent(activity, SecondActivityforResult::class.java)
            intent.putExtra("DataFromFirst","data")
            startActivityForResult(intent,101)*/
        }
      /* articleViewModel = ViewModelProvider(this).get(ArticleViewModel::class.java)
        homeAdapter = HomeAdapter(context!!, emptyList())
        fragmentHomeBinding!!.homeRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = homeAdapter
        }

        if(isDataPresent){
            articleViewModel!!.getDnaDataFromDb()
        }else{
        articleViewModel!!.getDnaData()
        }

        articleViewModel!!.getDnaDataFromDb().observe(viewLifecycleOwner, androidx.lifecycle.Observer{
            Log.e("observer","Call"+it.size)
          homeAdapter!!.setData(it)
        })*/
    }


   /* override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 101){
            Log.e("Fragment Result call",data!!.getStringExtra("ResultFromSecondActivity"))
        }
    }*/

    private fun  onPlay() {

/*
        fragmentHomeBinding!!.mButtonPlay.setOnClickListener(View.OnClickListener { // Disable the play button
            fragmentHomeBinding!!.mButtonPlay.setEnabled(false)
            val audioUrl = "http://www.all-birds.com/Sound/western%20bluebird.wav"
            mPlayer = MediaPlayer()
            // Set the media player audio stream type
            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
            //Try to play music/audio from url
            try {
                mPlayer.setDataSource(audioUrl)
                mPlayer.prepare()
                // Start playing audio from http url
                mPlayer.start()
                // Inform user for audio streaming
                Toast.makeText(context, "Playing", Toast.LENGTH_SHORT).show()
            } catch (e: IOException) {
                // Catch the exception
                e.printStackTrace()
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            } catch (e: SecurityException) {
                e.printStackTrace()
            } catch (e: IllegalStateException) {
                e.printStackTrace()
            }
            mPlayer.setOnCompletionListener(OnCompletionListener {
                Toast.makeText(context, "End", Toast.LENGTH_SHORT).show()
                fragmentHomeBinding!!.mButtonPlay.setEnabled(true)
            })
        })
*/
    }
}