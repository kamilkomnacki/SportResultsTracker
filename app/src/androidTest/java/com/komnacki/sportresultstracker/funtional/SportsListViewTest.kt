package com.komnacki.sportresultstracker.funtional

//import androidx.test.InstrumentationRegistry
//import androidx.test.rule.ActivityTestRule
//import androidx.test.runner.AndroidJUnit4
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.komnacki.sportresultstracker.R
import com.komnacki.sportresultstracker.funtional.Utils.RecyclerViewItemCountAssertion
import com.komnacki.sportresultstracker.funtional.Utils.TestUtils
import com.komnacki.sportresultstracker.sportsActivity.SportsListActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SportsListViewTest {
    private lateinit var stringToBetyped: String

    @get:Rule
    var activityRule: ActivityTestRule<SportsListActivity> = ActivityTestRule(SportsListActivity::class.java)

    @Before
    fun initValidString() {
        // Specify a valid string.
        stringToBetyped = "Espresso"
    }

    @Test
    fun testEmptyListView() {
        Thread.sleep(TestUtils.timeIntervalMillis)
        onView(withId(R.id.rv_sportsList))
                .check(RecyclerViewItemCountAssertion(0))

        onView(withText("The list of your sports is empty…"))
                .check(matches(isDisplayed()))

        onView(withId(R.id.fab))
                .check(matches(isDisplayed()))

        onView(withId(R.id.ic_empty_sportsList))
                .check(matches(isDisplayed()))

    }

}