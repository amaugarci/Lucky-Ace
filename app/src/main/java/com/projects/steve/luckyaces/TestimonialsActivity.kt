package com.projects.steve.luckyaces

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View

import kotlinx.android.synthetic.main.activity_testimonials.*

class TestimonialsActivity : AppCompatActivity() {

    private lateinit var mDrawerLayout: DrawerLayout

    private lateinit var viewPager: ViewPager

    private var index = 0

    private var testimonials  = arrayListOf(

        "Vishnu Das        1st 15 minutes : When I was looking for luck I got something very strange , I meet my old roommate .\n 2nd 15 minutes : I got something really interesting video in youtube which I was looking for a long time.\n 3rd 15 minutes : This was even better , I got a text message from my ex-love. \n Last 15 minutes : Last one was normal . I was looking for luck but unfortunately didn’t get any thing special. \n ",

        "jitesdugar says: My wife messaged me that her job appraisal was done and she got an “ A”  rating. Happy moment for both of us!  2. I was at my job and noticed some issues that could have negatively impacted our business in the coming weekend. Found those issues and asked my team to fix those. This is how looking for luck went for me!",

        "Submitted by A.N\n09:00 notification came\n09:15 nothing happened so far\n09:30 unexpectedly received a call from my spouse telling me that how lucky to have me\n09:45 still on the call enjoying this perfect and lucky moment.\n10:00 it was a perfect hour for me among last couple of days and made me realize the blessings of my life",

        "leenantonysays:\nI got the lucky notification at 12:10 PM. I was on a trip to my brother-in-law's home. Nothing happened significantly in an hour, but i started to focus on everything happening to me. To find something positive. Then i found the trip become easy in heavy traffic.  also, I made new friends in his family.  It happened today. I never believed in luck, because it never happened to me. But from today's experience i started to believe, i am   a lucky one. My way of thinking was wrong. It did help  me to think positive!",

        "usamaa says :\nI was extremely depressed and was travelling to University in the bus. I was worried about my father who had an accident two days ago  and I was also waiting for my 4th semester result. I received the first notification when I was in bus at 9:45am, I looked at it and heard the music which was played for five seconds. It was quite amusing to me. After 20 mins I received the call from my mother that my father had recovered from illness. I was extremely happy and this app proved to be very lucky for me. Then at 10:30 am I received an email from university about my result of 4th semester. I was surprised to see that I got (A) grade in all courses. This was all because of this app, I am extremely lucky to have this app in my phone. I highly recommend to everyone.",

        "swappyison says:\nFebruary 8:02pm; It had already been eight hours since the last i had this app “Johnny aces” in my phone. I was waiting for  the notification   maybe   fate had a lot of good in store for me. February 8:15 pm:  my last two submissions to a literary agent had already been god awful bad. This time I had a mail dropped that read something like this. “Your work reads out to be fair enough but maybe we could work together to really make it happen” For the next 15 minutes i was doodling on the bunch of loose A4 sheet papers trying to organize my ideas.I hadn’t ever held a pen for this long.\nI do not know what to call it. Maybe it was “Johnny aces” that had brought me this sheer good luck. I am rewriting my book and have this lucky feeling that it will get  it the printed soon enough.",

        "The app helped me realize that  your life is in your hands. Everything that happens in your life is a result of your own thoughts.\n We just need   to follow our heart,  and be sure in our own success.    E.P.",

        "Feeling Lucky?  Submit your Lucky Aces testimonial!",

        "rohitck007:\n11:23 AM\nFirst 15 Minutes : No luck found\nSecond 15 Minutes:\nThere is a trend in India about using Indian products these days, I got the new idea about starting a new website while watching the news regarding the same. I started working on it immediately.",

        "True Testimonial #24\n7 Mins: While playing a mobile card game I was rewarded with a legendary card.\n 30 Mins: No signs of luck.\n 45 Mins: No signs of luck.\n 58 Mins: Wow, just got off the phone with a woman from the super market. I entered a draw yesterday for a fridge and I won! I have received outstanding luck from this app today!\nby deancrouse2",

        "True Testimonial #9:\nI’d been waffling for a few days on whether or not to buy these models for a game that I play competitively. Today I found myself staring at the order page for ten minutes and  just as I was about to press buy when the lucky aces music played at about 6:15pm. My gut told me not to buy anything, so I closed the window. Minutes later, my brother calls to tell me that he just bought me the exact items, as an early birthday gift.",

        "macalisterball:\nI looked for luck and got nothin, but it provided a good hour of building focus, motivation and positive energy. ",

        "M.Daldo :\n Final Outcome: Since downloading this app I have noticed a large amount of luck headed my way. I never thought an app could really bring me luck , but this one has.",

        "True Testimonial #17:\nIt got me thinking about looking for opportunities in my average day. The Lucky Aces reminder came and the music played while I was waiting at my normal bus stop. I decided, “Maybe I’ll take another bus to get home.” I took a chance on another bus and ended up meeting someone.  Now I’ve been seeing her for the past week and a half !!! ",

        "True Testimonial #62 \nYes, It works. after the notification,  \nI received a friend request from a past friend. Now we are building a nice friendship  .\ncjmax006"

    )

    private var testimonials0 = arrayListOf("\n5:31 On the way home. 5.45 message from recruiter saying there is an interview next week. Now that is better luck, though it took more than an hour to come. \n - totoromori",

        "\nYes, it works. Once, after the notification, at around 10 mins, I received a friend request from an unknown girl. Now we are building a nice friendship from there. \n - cjmax006",

        "\n7 mins: While playing a mobile card game I was rewarded with a legendary card. \n 30 Mins: No signs of luck. \n 45 Mins: No signs of luck. \n 58 Mins: \"Wow, just got off the phone with a woman from the supermarket. I entered a draw yesterday for a fridge and I won! I have received outstanding luck from this app today!\" \n - deancrouse2",
        "\n9:00 notification came \n 9:15 nothing happened so far \n 9:30 unexpectedly received a call from my spouse telling me that how lucky he is to have me as a life partner \n 9:45 still on the call enjoying this perfect and lucky moment \n 10:00 it ws a perfect hour for me among last couple of days and made me realize the blessings of my life \n - A.N",
        "\nThe app helped me realize that all our life is in our hands. Everything that happens in our life is a result of our thoughts. We just need to have the courage to follow our heart, intuition and be sure in our own success. \n - E.P.",
        "\nI'd been waffling for a few days on whether or not to buy these models for a game that I play completitively. Today I found myself staring at the order page for ten minutes and just as I was about to press buy when the lucky aces music played at about 6:15pm. My gut told me not to buy anything, so I closed the window. Minutes later, my brother calls to tell me that he just bought me the exact items, as an early birthday gift.",
        "\nIt got me thinking about looking for opportunities in my average day. the LuckAces reminder came and the music played while I was waiting at my normal bus stop. I decided, \"Maybe I'll take another bus to get home.\" I took a chance on another bus and ended up meeting someone. Noe I've been seeing her for the past week and a half!!!",
        "\nI looked for luck for the 60 min. and got shit, but it provided a good hour of building focus, motivation and positive energy.",
        "\nIt had already been eight hours since the last I had this app \"Johnny aces\" in my phone. I was waiting for the phone to ring, the \"five seconds\" of the alert sound that pointed out maybe that fate had a lot good in store for me. \"What now!\" I sounded exasperated. 7 Febuaray 8:15 pm: The phone finally rang. I thought maybe I should have checked my mail. After all, my last two submissions to a literary agent had already been god awful bad. this time I had a mail dropped that read something like this. \"Your work reads out to be fair enough but maybe we could work together to really make it happen\" \n - swappyison",
        "\n1st 15 minutes : When I was looking for luck I got something very strange, I meet my roommate. \n 2nd 15 minutes : I got something really interesting video in youtube which I was looking for for a long time \n 3rd 15 minutes : This was even better, I got a text message from my ex-love. \n Last 15 minutes : Last one was normal. I was looking for luck but unfortunately didn't get anything special. \n - Vishnu Das",
        "\nFinal Outcome: \"Since downloading this app I have noticed a large mount of luck headed my way. I never thought an app could really bring me luck, but this one has\" \n - M. Daldo",
        "\nI got a notification at 12:10 PM. I was on a trip to brother-in-law's home. Nothing happened significantly in an hour. But I started to focus on everything happening to me. To find something positive. Then I found that the trip became easy in that heavy traffic. And I made new friends there in his family. I always wanted to escape from the irritating talks of his father, which feels me useless. It happened today. I never believed in lucks. Because it never happened to me. But from today's experience I started to believe, I am also a lucky one. My way of thinking was wrong. It helps me to think positive. \n - leenantonysays",
        "\nI ended up having a short conversation with a neighbor I hadn't seen in a while. I'm usually a pretty shy person, but since I was in the \"looking for luck\" mode, I decided to strike up a conversation, and it turned out to be a really pleasant one. \n - K.A",
        "\n11:23 AM \n First 15 Minutes : No luck found \n Second 15 Minutes: There is a trend in India about using Indian products these days, I got the new idea about starting a new websote while watching the news regarding the same, I started working on it immediately. \n - rohitck007",
        "\nMy wife messaged me that her job appraisal was done and she got an \"A\" rating. Happy moment for both of us! 2. I was at my job and noticed some issues that could have negatively impacted out business in the coming weekend. Found those issues and asked my team to fix those. This is how the challenge went for me! \n - jitesdugar",
        "\nDay Three: Notification went off at 9:53am. Nothing significant happened within the hour except that it woke me up. Because I was up , my husband decided to make me breakfast of his own accord which is a rare occurence. This prompted me to check his email where I found a new email with the possibility of a new job opportunity. \n - lindasmith988",
        "\nI was extremely depressed and was travelling to University in the bus. I was worried about my father who met accident two days ago and was in critical condition and I was also waiting for my 4th semester result. I received the first notification when I was in bus at 9:45am, I looked at it and heard the music which was played for five seconds. It was quite amusing to me. After 20 mins I received the call from my mother that my father had recovered from illness. I was extremely happy and this app proved to be very lucky to me. Then at 10:30 am I received an email from university about my result of 4th semester. I was suprised to see that I got (A) grade in all courses. this was all because of this app, I am extremely lucky to have this ap in my phone. I highly recomend to everyone. \n usamaa")



    private lateinit var pagerAdapter: TestimonialsPagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testimonials)
        setSupportActionBar(toolbar)


        viewPager = findViewById(R.id.viewPager)

        getSupportActionBar()?.title = "";

        pagerAdapter = TestimonialsPagerAdapter(supportFragmentManager, testimonials)
        viewPager.adapter = pagerAdapter

        setupDrawer()


        prevButton.isEnabled = false

        prevButton.setOnClickListener {
            if (index > 0) {
                index--

                viewPager.setCurrentItem(index)

                if (index == 0) {
                    prevButton.isEnabled = false
                }
                if (index == testimonials.size - 2) {
                    nextButton.isEnabled = true
                }
            }
        }

        nextButton.setOnClickListener {
            if (index < testimonials.size - 1) {
                index++

                viewPager.setCurrentItem(index)

                if (index == testimonials.size - 1) {
                    nextButton.isEnabled = false
                }
                if (index == 1) {
                    prevButton.isEnabled = true
                }
            }
        }

        createButton.setOnClickListener {
            val intent = Intent(this, CreateTestimonialActivity::class.java)
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
