package com.projects.steve.luckyaces

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.ShareCompat
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.widget.Toolbar
import android.text.method.ScrollingMovementMethod
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_story.*

class StoryActivity : AppCompatActivity() {

    private lateinit var mDrawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story)

        setupDrawer()

        getSupportActionBar()?.title = "";

        textView.movementMethod = ScrollingMovementMethod()

        luckyAcesButton.setOnClickListener {
            val intent = Intent(this, ChallengeActivity::class.java)
            // start your next activity
            startActivity(intent)
        }
    }

    private fun setupDrawer( ) {

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val actionbar: ActionBar? = supportActionBar
        actionbar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.burger_icon)
        }

        mDrawerLayout = findViewById(R.id.drawer_layout)
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            // set item as selected to persist highlight
            menuItem.isChecked = true
            // close drawer when item is tapped
            mDrawerLayout.closeDrawers()

            // Handle navigation view item clicks here.
            when (menuItem.itemId) {

                R.id.nav_home -> {

                    val intent = Intent(this, MainActivity::class.java)
                    // start your next activity
                    startActivity(intent)
                }
                R.id.nav_story -> {

                    val intent = Intent(this, StoryActivity::class.java)
                    // start your next activity
                    startActivity(intent)
                }
                R.id.nav_challenge -> {

                    val intent = Intent(this, ChallengeActivity::class.java)
                    // start your next activity
                    startActivity(intent)
                }
                R.id.nav_tesimonials -> {

                    val intent = Intent(this, TestimonialsActivity::class.java)
                    // start your next activity
                    startActivity(intent)
                }
                R.id.nav_gear -> {

                    val intent = Intent(this, WebviewActivity::class.java)
                    // start your next activity
                    startActivity(intent)
                }
                R.id.nav_share -> {

                    val share = Intent(android.content.Intent.ACTION_SEND)
                    share.type = "text/plain"
                    share.putExtra(Intent.EXTRA_SUBJECT, "Have you heard about the Lucky Aces?")
                    share.putExtra(Intent.EXTRA_TEXT, "http://www.johnnyaces.com/")

                    startActivity(Intent.createChooser(share, "Share text to..."))

                }

            }
            // Add code here to update the UI based on the item selected
            // For example, swap UI fragments here

            true

        }

    }

    //appbar - toolbar button click
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                mDrawerLayout.openDrawer(GravityCompat.START)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}


