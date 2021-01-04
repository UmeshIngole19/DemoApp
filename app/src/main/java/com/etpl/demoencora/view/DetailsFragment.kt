package com.etpl.demoencora.view

import android.annotation.SuppressLint
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.MediaPlayer.OnPreparedListener
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.etpl.demoencora.R
import com.etpl.demoencora.databinding.DetailsBinding


class DetailsFragment : Fragment() {
    var detailsBinding:DetailsBinding?=null
    lateinit var mPlayer:MediaPlayer
    var handler = Handler()
    var url = "https://music.apple.com/us/album/swimming-in-the-stars-single/1541357258?uo=2"


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
        detailsBinding = DetailsBinding.inflate(inflater,container,false)
        return detailsBinding!!.root
    }

    var updeter = Runnable {
        updateSeekBar()
        var currentDuration = mPlayer.currentPosition
        detailsBinding!!.startTextCount.text = milliSecondsToTimer(currentDuration)
    }

    private fun updateSeekBar(){
        if(mPlayer.isPlaying){
            detailsBinding!!.seekBar.progress = ((((mPlayer.currentPosition / mPlayer.duration) * 100).toFloat()).toInt())
            handler.postDelayed(updeter,1000)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mPlayer = MediaPlayer()
        detailsBinding!!.seekBar.max = 100

        detailsBinding!!.mButtonPlayPause.setOnClickListener{
            if(mPlayer.isPlaying){
                handler.removeCallbacks(updeter)
                mPlayer.pause()
                detailsBinding!!.mButtonPlayPause.setImageResource(R.drawable.play)
            }else{
                mPlayer.start()
                detailsBinding!!.mButtonPlayPause.setImageResource(R.drawable.pause)
                updateSeekBar()
            }
        }
        preparedMediaPlayer()

        detailsBinding!!.seekBar.setOnTouchListener { v, event ->
                var playPosition = ((mPlayer.duration / 100) * detailsBinding!!.seekBar.progress)
                mPlayer.seekTo(playPosition)
                detailsBinding!!.startTextCount.text = milliSecondsToTimer(mPlayer.currentPosition)
                false
        }
    }

    private fun preparedMediaPlayer(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mPlayer.setAudioAttributes(AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setLegacyStreamType(AudioManager.STREAM_MUSIC)
                    .build())
        } else {
            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
        }

            try {
                mPlayer.setDataSource(url)
                mPlayer.prepare()
                Log.e("Song Durations",mPlayer.duration.toString())
                detailsBinding!!.endTextCount.text = milliSecondsToTimer(mPlayer.duration)

            } catch (e: Exception) {
                e.printStackTrace()
            }
    }

    private fun  milliSecondsToTimer(milliseconds:Int):String {
        var timerString=""
        var secondsString=""
        var hours = (milliseconds / 1000 * 60 * 60)
        var minutes = ((milliseconds % 1000 * 60 *60) / (1000 * 60))
        var seconds = (((milliseconds % 1000 * 60 * 60) / (1000 * 60)) / 1000)

        if(hours > 0){
            timerString = "$hours;"
        }
        secondsString = if(seconds < 10){
            "0$seconds"
        }else{
            " $seconds"
        }

        timerString = "$timerString$minutes:$secondsString"
        return timerString
    }
}