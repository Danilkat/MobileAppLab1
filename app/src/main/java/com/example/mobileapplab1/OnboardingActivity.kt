package com.example.mobileapplab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2

class OnboardingActivity : AppCompatActivity() {

    private lateinit var onboardingItemsAdapter: OnboardingItemsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        setOnboardingItems()
    }

    private fun setOnboardingItems() {
        onboardingItemsAdapter = OnboardingItemsAdapter(
            listOf(
                OnboardingItem(
                    onboardingImage = R.drawable.cool_kids_long_distance_relationship,
                    title = "Learn anytime and anywhere",
                    description = "Quarantine is the perfect time to spend your day " +
                            "learning something new, from anywhere!"
                ),
                OnboardingItem(
                    onboardingImage = R.drawable.cool_kids_staying_home,
                    title = "Find a course for you",
                    description = "Quarantine is the perfect time to spend your day " +
                            "learning something new, from anywhere!"
                ),
                OnboardingItem(
                    onboardingImage = R.drawable.cool_kids_high_tech,
                    title = "Improve your skills",
                    description = "Quarantine is the perfect time to spend your day " +
                            "learning something new, from anywhere!"
                ),
            )

        )

        val onboardingViewPager = findViewById<ViewPager2>(R.id.onboardingViewPager)
        onboardingViewPager.adapter = onboardingItemsAdapter
    }
}