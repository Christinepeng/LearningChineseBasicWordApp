package com.renameExample.android.learnChinese

import android.media.AudioManager
import android.media.AudioManager.OnAudioFocusChangeListener
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView

class ColorsActivity : AppCompatActivity() {
    private var mMediaPlayer: MediaPlayer? = null
    private var mAudioManager: AudioManager? = null
    private val mCompletionListener = OnCompletionListener { releaseMediaPlayer() }
    private val mOnAudioFocusChangeListener = OnAudioFocusChangeListener { focusChange ->
        if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK
        ) {
            mMediaPlayer!!.pause()
            mMediaPlayer!!.seekTo(0)
        } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
            mMediaPlayer!!.start()
        } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
            releaseMediaPlayer()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.word_list)
        mAudioManager = getSystemService(AUDIO_SERVICE) as AudioManager
        val words = ArrayList<Word>()
        words.add(Word("red", "紅色", R.drawable.color_red, R.raw.color_red))
        words.add(
            Word(
                "mustard yellow", "芥末黃", R.drawable.color_mustard_yellow,
                R.raw.color_mustard_yellow
            )
        )
        words.add(
            Word(
                "dusty yellow", "土黃色", R.drawable.color_dusty_yellow,
                R.raw.color_dusty_yellow
            )
        )
        words.add(Word("green", "綠色", R.drawable.color_green, R.raw.color_green))
        words.add(Word("brown", "咖啡色", R.drawable.color_brown, R.raw.color_brown))
        words.add(Word("gray", "灰色", R.drawable.color_gray, R.raw.color_gray))
        words.add(Word("black", "黑色", R.drawable.color_black, R.raw.color_black))
        words.add(Word("white", "白色", R.drawable.color_white, R.raw.color_white))
        val adapter = WordAdapter(this, words, R.color.category_colors)
        val listView = findViewById<View>(R.id.list) as ListView
        listView.setAdapter(adapter)
        listView.onItemClickListener = OnItemClickListener { adapterView, view, position, l ->
            releaseMediaPlayer()
            val word = words[position]
            val result = mAudioManager!!.requestAudioFocus(
                mOnAudioFocusChangeListener,
                AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT
            )
            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                mMediaPlayer = MediaPlayer.create(this@ColorsActivity, word.getmAudioResourceId())
                mMediaPlayer!!.start()
                mMediaPlayer!!.setOnCompletionListener(mCompletionListener)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        releaseMediaPlayer()
    }

    private fun releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer!!.release()
            mMediaPlayer = null
            mAudioManager!!.abandonAudioFocus(mOnAudioFocusChangeListener)
        }
    }
}
