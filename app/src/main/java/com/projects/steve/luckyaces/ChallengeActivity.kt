package com.projects.steve.luckyaces

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.Toast

//import com.projects.steve.luckyaces.notif



import kotlinx.android.synthetic.main.activity_challenge.*
import java.util.*


class ChallengeActivity : AppCompatActivity() {

    private lateinit var mDrawerLayout: DrawerLayout

    private var mButtonToggled = false

    private val SHARED_PREF = "sharedPreferences"


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge)
        setSupportActionBar(toolbar)

        setupDrawer()

        getSupportActionBar()?.title = "";


        // update ui for on off state

        val sharedPreferences = getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)

        val challengeActive = sharedPreferences.getBoolean("challengeActive", false)

        if (challengeActive) {
            challengeButtonOn()
            mButtonToggled = true
        }
        else {
            challengeButtonOff()
            mButtonToggled = false
        }



        challengeToggleButton.setOnClickListener  {

            if (!mButtonToggled) {

//                challengeButtonOn()
                mButtonToggled = true

                //challengeButton.isSelected = true

                challengeButtonOn()
                beginPopup()

            }
            else {
                mButtonToggled = false
//                challengeButtonOff()

                challengeButtonOff()
//                cancelPopup()
            }



        }

    }



    private fun beginPopup() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.notif_title));
        builder.setMessage(getString(R.string.notif_body));
        //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

        builder.setPositiveButton(android.R.string.ok) { dialog, which ->
            beginChallenge()
        }

        builder.show()
    }

    private fun cancelPopup() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Challenge cancelled")
        builder.setMessage("Try again when you want to look for luck!")
        //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

        builder.setPositiveButton(android.R.string.ok) { dialog, which ->
            cancelChallenge()
        }

        builder.show()
    }

    private fun beginChallenge() {
        var timeBetweenNowAndOneDay = 60*60*24*1000; // 60 secs * 60 minutes * 24 hours * 1000 for milliseconds

        // ten second
        val notificationTime = Calendar.getInstance().timeInMillis + timeBetweenNowAndOneDay // 10000

        val sharedPreferences = getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)

        NotificationUtil().setNotification(notificationTime, this@ChallengeActivity)
        sharedPreferences.edit().putBoolean("challengeActive", true).commit()

        challengeButtonOn()
    }

    private fun cancelChallenge() {
        NotificationUtil().cancelNotification(this@ChallengeActivity)

        challengeButtonOff()
    }

    private fun challengeButtonOn() {

        challengeToggleButton.setBackgroundResource(R.drawable.toggle_state_on);
        challengeToggleButton.setTextColor(getResources().getColor(R.color.colorWhite))

    }

    private fun challengeButtonOff() {


        challengeToggleButton.setBackgroundResource(R.drawable.toggle_state_off); challengeToggleButton.setTextColor(getResources().getColor(R.color.colorChallengeButtonBackground))

    }

    /*
    fun createLocalNotification() {

        val context = this

        val title = getString(R.string.notif_title);

        val message = getString(R.string.notif_body);

        // 1
        val channelId = "${context.packageName}-${context.getString(R.string.app_name)}"
// 2
        val notificationBuilder = NotificationCompat.Builder(context, channelId).apply {
            setSmallIcon(R.drawable.ic_launcher_background) // 3
            setContentTitle(title) // 4
            setContentText(message) // 5
            setStyle(NotificationCompat.BigTextStyle().bigText("big text")) // 6
            priority = NotificationCompat.PRIORITY_DEFAULT // 7
//            setAutoCancel(autoCancel) // 8
        }

        // 1
        val notificationManager = NotificationManagerCompat.from(context)
        // 2
        notificationManager.notify(1001, notificationBuilder.build())
    }

    fun createNotificationChannel(context: Context, importance: Int, showBadge: Boolean, name: String, description: String) {
        // 1
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // 2
            val channelId = "${context.packageName}-$name"
            val channel = NotificationChannel(channelId, name, importance)
            channel.description = description
            channel.setShowBadge(showBadge)

            // 3
            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }
    */


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

                    val share = Intent(Intent.ACTION_SEND)
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
