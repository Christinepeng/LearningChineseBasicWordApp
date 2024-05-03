package com.renameExample.android.learnChinese.view


import android.media.AudioManager
import android.media.AudioManager.OnAudioFocusChangeListener
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.renameExample.android.learnChinese.R
import com.renameExample.android.learnChinese.databinding.WordListBinding
import com.renameExample.android.learnChinese.model.Word
import com.renameExample.android.learnChinese.viewModel.FamilyViewModel

class FamilyActivity : AppCompatActivity(), WordItemClickListener  {
    private val binding: WordListBinding by lazy { WordListBinding.inflate(layoutInflater) }
    private lateinit var viewModel: FamilyViewModel
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
        setContentView(binding.root)
        mAudioManager = getSystemService(AUDIO_SERVICE) as AudioManager
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(FamilyViewModel::class.java)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val adapter = WordAdapter(this, ArrayList(), this, R.color.category_family)
        binding.wordRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.wordRecyclerView.adapter = adapter as RecyclerView.Adapter<RecyclerView.ViewHolder>

        viewModel.words.observe(this, Observer { words ->
            words?.let {
                adapter.addAll(words)
            }
        })
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

    override fun onWordClicked(word: Word) {
        releaseMediaPlayer()
        val result = mAudioManager!!.requestAudioFocus(
            mOnAudioFocusChangeListener,
            AudioManager.STREAM_MUSIC,
            AudioManager.AUDIOFOCUS_GAIN_TRANSIENT
        )
        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            mMediaPlayer = MediaPlayer.create(this@FamilyActivity, word.getmAudioResourceId())
            mMediaPlayer?.start()
            mMediaPlayer?.setOnCompletionListener(mCompletionListener)
        }
    }
}