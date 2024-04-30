package com.renameExample.android.learnChinese.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
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
import com.renameExample.android.learnChinese.viewModel.ColorsViewModel
import com.renameExample.android.learnChinese.viewModel.PhrasesViewModel

class PhrasesActivity : AppCompatActivity() {
    private lateinit var viewModel: PhrasesViewModel
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
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(PhrasesViewModel::class.java)
        setupListView()
    }

    private fun setupListView() {

        val adapter = WordAdapter(this, ArrayList(), R.color.category_phrases)
        val listView = findViewById<View>(R.id.list) as ListView
        listView.adapter = adapter
        viewModel.words.observe(this, Observer { words ->
            words?.let {
                adapter.addAll(words)
            }
        })

        listView.onItemClickListener = OnItemClickListener { adapterView, view, position, l ->
            releaseMediaPlayer()
            val word = adapter.getItem(position)
            val result = mAudioManager!!.requestAudioFocus(
                mOnAudioFocusChangeListener,
                AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT
            )
            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                mMediaPlayer = MediaPlayer.create(this@PhrasesActivity, word!!.getmAudioResourceId())
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