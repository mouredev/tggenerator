package com.mouredev.tenerifegg.audio

import android.content.Context
import android.media.MediaRecorder
import android.os.Build
import java.io.File
import java.io.FileOutputStream

/**
 * Created by MoureDev by Brais Moure on 9/7/23.
 * www.mouredev.com
 */
class AudioRecorder(private val context: Context) {

    private var recorder: MediaRecorder? = null

    private fun create(): MediaRecorder {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            MediaRecorder(context)
        } else MediaRecorder()
    }

    fun record(file: File) {
        create().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setOutputFile(FileOutputStream(file).fd)

            prepare()
            start()

            recorder = this
        }
    }

    fun stop() {
        recorder?.stop()
        recorder?.reset()
        recorder = null
    }

}