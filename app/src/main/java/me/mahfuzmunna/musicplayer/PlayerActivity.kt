package me.mahfuzmunna.musicplayer

import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import me.mahfuzmunna.musicplayer.databinding.ActivityPlayerBinding
import me.mahfuzmunna.musicplayer.views.ItemAdapter
import kotlin.coroutines.CoroutineContext


class PlayerActivity : AppCompatActivity(), CoroutineScope {
    private lateinit var binding: ActivityPlayerBinding

    private var job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.playerRecycler.setHasFixedSize(true)
        binding.playerRecycler.setItemViewCacheSize(20)
        binding.playerRecycler.layoutManager = LinearLayoutManager(this)

        launch {
            val musicItems = loadMusic()

            binding.playerRecycler.layoutManager = LinearLayoutManager(this@PlayerActivity)
            binding.playerRecycler.adapter = ItemAdapter(musicItems)
        }

    }

    suspend fun loadMusic(): ArrayList<String> {
        val musics = arrayListOf<String>()
        val contentLocation: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val selection = MediaStore.Audio.Media.IS_MUSIC + " != 0"
        val projections = arrayOf<String>(
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ARTIST_ID,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.COMPOSER,
            MediaStore.Audio.Media.SIZE,
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.BUCKET_ID
        )

        val cursor: Cursor? =
            contentResolver.query(contentLocation, projections, selection, null, null)

        if (cursor != null && cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                musics.add(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)))
            }
        }
        cursor?.close()
        return musics
    }
}