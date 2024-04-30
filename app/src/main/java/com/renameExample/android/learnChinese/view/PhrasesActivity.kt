package com.renameExample.android.learnChinese.view

import android.media.AudioManager
import android.media.AudioManager.OnAudioFocusChangeListener
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import com.renameExample.android.learnChinese.R
import com.renameExample.android.learnChinese.model.Word

class PhrasesActivity : AppCompatActivity() {
    private var mMediaPlayer: MediaPlayer? = null
    private var mAudioManager: AudioManager? = null
    private val mCompletionListener = OnCompletionListener { releaseMediaPlayer() }
    private val mOnAudioFocusChangeListener = OnAudioFocusChangeListener { focusChange ->
        when (focusChange) {
            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT,
            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK -> {
                mMediaPlayer?.pause()
                mMediaPlayer?.seekTo(0)
            }

            AudioManager.AUDIOFOCUS_GAIN -> mMediaPlayer?.start()
            AudioManager.AUDIOFOCUS_LOSS -> releaseMediaPlayer()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.word_list)
        mAudioManager = getSystemService(AUDIO_SERVICE) as AudioManager
        setupListView()
    }
    private fun setupListView() {
        val words = ArrayList<Word>()
        words.add(
            Word(
                "Where are you going?", "你要去哪裡？",
                R.raw.phrase_where_are_you_going
            )
        )
        words.add(
            Word(
                "What is your name?", "你叫什麼名字？",
                R.raw.phrase_what_is_your_name
            )
        )
        words.add(Word("My name is...", "我的名字是...", R.raw.phrase_my_name_is))
        words.add(Word("How are you feeling?", "你感覺怎麼樣？", R.raw.phrase_how_are_you_feeling))
        words.add(Word("I’m feeling good.", "我感覺很好。", R.raw.phrase_im_feeling_good))
        words.add(Word("Are you coming?", "你來嗎？", R.raw.phrase_are_you_coming))
        words.add(Word("Yes, I’m coming.", "是的，我來了。", R.raw.phrase_yes_im_coming))
        words.add(Word("I’m coming.", "我來了。", R.raw.phrase_im_coming))
        words.add(Word("Let’s go.", "走吧。", R.raw.phrase_lets_go))
        words.add(Word("Come here.", "來這裡。", R.raw.phrase_come_here))
        val adapter = WordAdapter(this, words, R.color.category_phrases)
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
                mMediaPlayer = MediaPlayer.create(this@PhrasesActivity, word.getmAudioResourceId())
                mMediaPlayer?.start()
                mMediaPlayer?.setOnCompletionListener(mCompletionListener)
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
            mAudioManager?.abandonAudioFocus(mOnAudioFocusChangeListener)
        }
    }
}