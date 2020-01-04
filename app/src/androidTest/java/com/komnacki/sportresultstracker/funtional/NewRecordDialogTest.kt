package com.komnacki.sportresultstracker.funtional

//import androidx.test.InstrumentationRegistry
//import androidx.test.rule.ActivityTestRule
//import androidx.test.runner.AndroidJUnit4
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.RootMatchers.isDialog
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.text.InputType
import android.util.Log
import com.komnacki.sportresultstracker.R
import com.komnacki.sportresultstracker.funtional.Utils.RecyclerViewItemCountAssertion
import com.komnacki.sportresultstracker.funtional.Utils.TestUtils
import com.komnacki.sportresultstracker.recordsActivity.RecordsListActivity
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class NewRecordDialogTest {
    private lateinit var stringToBetyped: String

    @get:Rule
    var activityRule: ActivityTestRule<RecordsListActivity> = ActivityTestRule(RecordsListActivity::class.java)

    @Before
    fun initValidString() {
        // Specify a valid string.
        stringToBetyped = "Espresso"
    }

    @Test
    fun testInputTypesAddRecordDialog() {

        Thread.sleep(TestUtils.timeIntervalMillis)
        onView(withText("The list of your records is empty…"))
                .check(matches(isDisplayed()))

        onView(withId(R.id.rv_recordsList))
                .check(RecyclerViewItemCountAssertion(0))

        onView(withId(R.id.fab)).perform(click())

        onView(withText("Your record"))
                .check(matches(isDisplayed()))

        Thread.sleep(TestUtils.timeIntervalMillis)

        Thread.sleep(TestUtils.timeIntervalMillis)
        onView(withId(R.id.et_time_recordEdit))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click())
                .check(matches(allOf(withInputType(InputType.TYPE_CLASS_NUMBER))))

        Thread.sleep(TestUtils.timeIntervalMillis)
        onView(withId(R.id.et_time_recordEdit))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click())
                .perform(typeText("123456789"))
                .perform(pressBack())

        Thread.sleep(TestUtils.timeIntervalMillis)
        onView(withId(R.id.et_distance_recordEdit))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click())
                .check(matches(allOf(withInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL + InputType.TYPE_CLASS_NUMBER))))

        Log.d("asd", "1")
        Thread.sleep(TestUtils.timeIntervalMillis)
        onView(withId(R.id.et_distance_recordEdit))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click())
                .perform(typeText("146"))
                .perform(pressBack())
    }

}