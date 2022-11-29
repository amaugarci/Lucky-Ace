package com.projects.steve.luckyaces

import android.content.Intent
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mediaPlayer:MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // intro theme
        mediaPlayer = MediaPlayer.create(this, R.raw.ja3_6secmusic)
        mediaPlayer?.start()

        // button controls
        storyButton.setOnClickListener {
            val intent = Intent(this, StoryActivity::class.java)
            // start your next activity
            startActivity(intent)
        }

        acesButton.setOnClickListener {
            val intent = Intent(this, ChallengeActivity::class.java)
            // start your next activity
            startActivity(intent)
        }
    }


}
