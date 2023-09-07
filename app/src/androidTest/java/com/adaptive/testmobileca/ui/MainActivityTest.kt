package com.adaptive.testmobileca.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.adaptive.testmobileca.R
import org.junit.Rule
import org.junit.Test

/**
 * Created by Arno ABOMO on 09/06/2023
 */

/*
 * We add some basic tests to check if the activity is launched
 */

class MainActivityTest{

    @get: Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun is_Activity_launched() {
        onView(withId(R.id.bottom_nav_view)).check(matches(isDisplayed()))
    }

}