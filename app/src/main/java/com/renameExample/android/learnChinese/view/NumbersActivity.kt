package com.renameExample.android.learnChinese.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.media.AudioManager
import android.media.AudioManager.OnAudioFocusChangeListener
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.AdapterView.OnItemClickListener
import com.renameExample.android.learnChinese.R
import com.renameExample.android.learnChinese.databinding.WordListBinding
import com.renameExample.android.learnChinese.viewModel.NumbersViewModel

class NumbersActivity : AppCompatActivity() {
    private val binding: WordListBinding by lazy { WordListBinding.inflate(layoutInflater) }
    private lateinit var viewModel: NumbersViewModel
    private var mMediaPlayer: MediaPlayer? = null
    private var mAudioManager: AudioManager? = null
    var mOnAudioFocusChangeListener = OnAudioFocusChangeListener { focusChange ->
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
    private val mCompletionListener = OnCompletionListener { releaseMediaPlayer() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mAudioManager = getSystemService(AUDIO_SERVICE) as AudioManager
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NumbersViewModel::class.java)
        setupListView()
    }

    private fun setupListView() {

        val adapter = WordAdapter(this, ArrayList(), R.color.category_numbers)
        binding.list.adapter = adapter

        viewModel.words.observe(this, Observer { words ->
            words?.let {
                adapter.addAll(words)
            }
        })

        binding.list.onItemClickListener = OnItemClickListener { adapterView, view, position, id ->
            val word = adapter.getItem(position)
            releaseMediaPlayer()
            val result = mAudioManager!!.requestAudioFocus(
                mOnAudioFocusChangeListener,
                AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT
            )
            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                mMediaPlayer =
                    MediaPlayer.create(this@NumbersActivity, word!!.getmAudioResourceId())
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