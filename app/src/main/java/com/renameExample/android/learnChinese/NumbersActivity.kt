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

class NumbersActivity : AppCompatActivity() {
    private var mMediaPlayer: MediaPlayer? = null
    private var mAudioManager: AudioManager? = null
    var mOnAudioFocusChangeListener = OnAudioFocusChangeListener { focusChange ->
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
    private val mCompletionListener = OnCompletionListener { releaseMediaPlayer() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.word_list)
        mAudioManager = getSystemService(AUDIO_SERVICE) as AudioManager
        val words = ArrayList<Word>()
        words.add(Word("one", "一", R.drawable.number_one, R.raw.number_one))
        words.add(Word("two", "二", R.drawable.number_two, R.raw.number_two))
        words.add(Word("three", "三", R.drawable.number_three, R.raw.number_three))
        words.add(Word("four", "四", R.drawable.number_four, R.raw.number_four))
        words.add(Word("five", "五", R.drawable.number_five, R.raw.number_five))
        words.add(Word("six", "六", R.drawable.number_six, R.raw.number_six))
        words.add(Word("seven", "七", R.drawable.number_seven, R.raw.number_seven))
        words.add(Word("eight", "八", R.drawable.number_eight, R.raw.number_eight))
        words.add(Word("nine", "九", R.drawable.number_nine, R.raw.number_nine))
        words.add(Word("ten", "十", R.drawable.number_ten, R.raw.number_ten))
        val adapter = WordAdapter(this, words, R.color.category_numbers)
        val listView = findViewById<View>(R.id.list) as ListView
        listView.setAdapter(adapter)
        listView.onItemClickListener = OnItemClickListener { adapterView, view, position, id ->
            val word = words[position]
            releaseMediaPlayer()
            val result = mAudioManager!!.requestAudioFocus(
                mOnAudioFocusChangeListener,
                AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT
            )
            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                mMediaPlayer = MediaPlayer.create(this@NumbersActivity, word.getmAudioResourceId())
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