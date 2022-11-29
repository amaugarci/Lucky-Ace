package com.projects.steve.luckyaces


import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

//import com.squareup.picasso.Picasso

class TestimonialsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:
    Bundle?): View? {

        // Creates the view controlled by the fragment
        val view = inflater.inflate(R.layout.fragment_testimonials, container, false)
        val textView = view.findViewById<TextView>(R.id.TextView)
        textView.movementMethod = ScrollingMovementMethod()

        val args = arguments
        textView.text = args?.getString("Testimonial")

        return view
    }

    companion object {

        // Method for creating new instances of the fragment
        fun newInstance(testimonial: String): TestimonialsFragment {

            // Store the movie data in a Bundle object
            val args = Bundle()
            args.putString("Testimonial", testimonial)

            val fragment = TestimonialsFragment()
            fragment.arguments = args
            return fragment
        }
    }

}

