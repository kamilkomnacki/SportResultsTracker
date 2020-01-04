package com.komnacki.sportresultstracker.funtional.Utils

import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.matcher.ViewMatchers.assertThat
import android.support.v4.view.ViewPager
import android.view.View
import org.hamcrest.Matchers.`is`


class ChartContainerCountAssertion(private val expectedCount: Int) : ViewAssertion {
    override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }

        val viewPager = view as ViewPager
        val adapter = viewPager.adapter
        assertThat(adapter.count, `is`(expectedCount))
    }
}