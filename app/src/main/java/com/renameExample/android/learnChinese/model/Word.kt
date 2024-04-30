package com.renameExample.android.learnChinese.model

class Word {
    var defaultTranslation: String
        private set
    var miwokTranslation: String
        private set
    private var mImageResourceId = NO_IMAGE_PROVIDED
    private var mAudioResourceId: Int

    constructor(defaultTranslation: String, chineseTranslation: String, audioResourceId: Int) {
        this.defaultTranslation = defaultTranslation
        miwokTranslation = chineseTranslation
        mAudioResourceId = audioResourceId
    }

    constructor(
        defaultTranslation: String,
        chineseTranslation: String,
        imageResourceId: Int,
        audioResourceId: Int
    ) {
        this.defaultTranslation = defaultTranslation
        miwokTranslation = chineseTranslation
        mImageResourceId = imageResourceId
        mAudioResourceId = audioResourceId
    }

    fun getmImageResourceId(): Int {
        return mImageResourceId
    }

    fun hasImage(): Boolean {
        return mImageResourceId != NO_IMAGE_PROVIDED
    }

    fun getmAudioResourceId(): Int {
        return mAudioResourceId
    }

    companion object {
        private const val NO_IMAGE_PROVIDED = -1
    }
}
