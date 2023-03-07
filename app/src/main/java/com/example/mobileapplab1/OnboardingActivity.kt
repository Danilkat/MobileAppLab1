package com.example.mobileapplab1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.button.MaterialButton

class OnboardingActivity : AppCompatActivity() {

    private lateinit var onboardingItemsAdapter: OnboardingItemsAdapter
    private lateinit var indicatorsContainer: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        setOnboardingItems()
        setupIndicators()
        setCurrentIndicator(0)
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
        onboardingViewPager.registerOnPageChangeCallback(object :
        ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
                updateNextText(position)
            }
        })
        (onboardingViewPager.getChildAt(0) as RecyclerView).overScrollMode =
            RecyclerView.OVER_SCROLL_NEVER
        findViewById<MaterialButton>(R.id.buttonNext).setOnClickListener {
            if (onboardingViewPager.currentItem + 1 < onboardingItemsAdapter.itemCount) {
                onboardingViewPager.currentItem += 1
            } else {
                navigateToSignupActivity()
            }
        }
        findViewById<TextView>(R.id.textSkip).setOnClickListener {
            navigateToSignupActivity()
        }
    }

    private fun navigateToSignupActivity() {
        startActivity(Intent(applicationContext, SignupActivity::class.java))
        finish()
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

    private fun setCurrentIndicator(position: Int) {
        val childCount = indicatorsContainer.childCount
        for (i in 0 until childCount) {
            val imageView = indicatorsContainer.getChildAt(i) as ImageView
            if (i == position) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active_background
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )
            }
        }
    }

    private fun updateNextText(position: Int) {
        val nextText = findViewById<Button>(R.id.buttonNext)
        if (position == indicatorsContainer.childCount - 1) {
            nextText.text = getString(R.string.onboarding_start)
         } else {
            nextText.text = getString(R.string.onboarding_next)
        }
    }
}