package me.mahfuzmunna.musicplayer.models

import android.net.Uri
data class Track(
    var name: String,
    val title: String,
    val filePath: String,
    val artist: String,
    val album: String,
    val genre: String,
    val composer: String,
    val art_uri: Uri,
    val musicSize: Long,
    val duration: Long,
    val musicId: Long,
    val musicUri: String
)