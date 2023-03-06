package com.example.mobileapplab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2

class OnboardingActivity : AppCompatActivity() {

    private lateinit var onboardingItemsAdapter: OnboardingItemsAdapter
    private lateinit var indicatorsContainer: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        setOnboardingItems()
        setupIndicators()
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

    private fun setupIndicators() {
        indicatorsContainer = findViewById(R.id.indicatorsContainer)
        val indicators = arrayOfNulls<ImageView>(onboardingItemsAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )
                it.layoutParams = layoutParams
                indicatorsContainer.addView(it)
            }
        }

    }
}