package com.projects.steve.luckyaces

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_create_tesimonial.*

class CreateTestimonialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_tesimonial)

        getSupportActionBar()?.title = "";

        backButton.setOnClickListener {
            // got back
            onBackPressed()
        }

        submitButton.setOnClickListener {
//            val intent = Intent(this, StoryActivity::class.java)
//            // start your next activity
//            onBackPressed()

            val title = nameEditText.text.toString()
            val toEmail = "johnnyacesapp@gmail.com"
            val fromEmail = emailEditText.text.toString()
            val testimonial = testimonailEditText.text.toString()

            val uriText = "mailto:" + toEmail +
                    "?subject=" + Uri.encode(title) +
                    "&body=" + Uri.encode(testimonial)

            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:") // only email apps should handle this
            intent.putExtra(Intent.EXTRA_EMAIL, "johnnyacesapp@gmail.com")
            intent.putExtra(Intent.EXTRA_SUBJECT, testimonial)
            if (intent.resolveActivity(this@CreateTestimonialActivity.packageManager) != null) {
                startActivity(intent)
            }
        }
    }

}
