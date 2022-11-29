package com.projects.steve.luckyaces

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

// 1
class TestimonialsPagerAdapter(fragmentManager: FragmentManager, private val testimonials: ArrayList<String>) :
    FragmentStatePagerAdapter(fragmentManager) {

    // 2
    override fun getItem(position: Int): Fragment {
        return TestimonialsFragment.newInstance(testimonials[position])
    }

    // 3
    override fun getCount(): Int {
        return testimonials.size
    }
}