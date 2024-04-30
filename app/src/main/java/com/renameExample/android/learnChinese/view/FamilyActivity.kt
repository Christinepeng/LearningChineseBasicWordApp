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

class FamilyActivity : AppCompatActivity() {
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
        words.add(Word("father", "爸爸", R.drawable.family_father, R.raw.family_father))
        words.add(Word("mother", "媽媽", R.drawable.family_mother, R.raw.family_mother))
        words.add(Word("son", "兒子", R.drawable.family_son, R.raw.family_son))
        words.add(Word("daughter", "女兒", R.drawable.family_daughter, R.raw.family_daughter))
        words.add(
            Word(
                "older brother", "哥哥", R.drawable.family_older_brother,
                R.raw.family_older_brother
            )
        )
        words.add(
            Word(
                "younger brother", "弟弟", R.drawable.family_younger_brother,
                R.raw.family_younger_brother
            )
        )
        words.add(
            Word(
                "older sister", "姊姊", R.drawable.family_older_sister,
                R.raw.family_older_sister
            )
        )
        words.add(
            Word(
                "younger sister", "妹妹", R.drawable.family_younger_sister,
                R.raw.family_younger_sister
            )
        )
        words.add(
            Word(
                "grandmother ", "奶奶", R.drawable.family_grandmother,
                R.raw.family_grandmother
            )
        )
        words.add(
            Word(
                "grandfather", "爺爺", R.drawable.family_grandfather,
                R.raw.family_grandfather
            )
        )
        val adapter = WordAdapter(this, words, R.color.category_family)
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
                mMediaPlayer = MediaPlayer.create(this@FamilyActivity, word.getmAudioResourceId())
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