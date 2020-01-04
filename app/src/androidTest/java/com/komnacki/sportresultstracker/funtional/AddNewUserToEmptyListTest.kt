package com.komnacki.sportresultstracker.funtional

//import androidx.test.InstrumentationRegistry
//import androidx.test.rule.ActivityTestRule
//import androidx.test.runner.AndroidJUnit4
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeTextIntoFocusedView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.RootMatchers
import android.support.test.espresso.matcher.RootMatchers.isDialog
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.komnacki.sportresultstracker.R
import com.komnacki.sportresultstracker.funtional.Utils.RecyclerViewItemCountAssertion
import com.komnacki.sportresultstracker.funtional.Utils.TestUtils
import com.komnacki.sportresultstracker.usersActivity.UsersListActivity
import org.hamcrest.core.IsNot
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddNewUserToEmptyListTest {
    private lateinit var stringToBetyped: String

    @get:Rule
    var activityRule: ActivityTestRule<UsersListActivity> = ActivityTestRule(UsersListActivity::class.java)

    @Before
    fun initValidString() {
        // Specify a valid string.
        stringToBetyped = "Espresso"
    }

    @Test
    fun testIfAddNewUserDialogAppear() {
        var listCount = 0
        onView(withId(R.id.rv_usersList)).check { view, noViewFoundException ->
            listCount = TestUtils.getSizeOfRecyclerView(view as RecyclerView)
        }

        while (listCount > 0) {
            onView(withId(R.id.item_usersList_btn_delete)).perform(click())

            onView(withText("Delete user"))
                    .inRoot(RootMatchers.withDecorView(IsNot.not(activityRule.activity.window.decorView)))
                    .check(matches(isDisplayed()))

            onView(withId(android.R.id.button1))
                    .perform(click())

            onView(withId(R.id.rv_usersList)).check { view, noViewFoundException ->
                listCount = TestUtils.getSizeOfRecyclerView(view as RecyclerView)
            }
        }

        onView(withText("The list of users is empty…"))
                .check(matches(isDisplayed()))

        onView(withId(R.id.fab))
                .check(matches(isDisplayed()))

        onView(withId(R.id.ic_empty_usersList))
                .check(matches(isDisplayed()))

        Thread.sleep(TestUtils.timeIntervalMillis)
        onView(withId(R.id.fab)).perform(click())

        Thread.sleep(TestUtils.timeIntervalMillis)
        onView(withText("Add new user"))
                .check(matches(isDisplayed()))

        Thread.sleep(TestUtils.timeIntervalMillis)
        onView(withId(R.id.tv_input_user))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click())
                .perform(typeTextIntoFocusedView("Kamil"))

        Thread.sleep(TestUtils.timeIntervalMillis)
        onView(withId(android.R.id.button1))
                .perform(click())

        Thread.sleep(TestUtils.timeIntervalMillis)
        onView(withId(R.id.rv_usersList))
                .check(RecyclerViewItemCountAssertion(1))

        onView(withId(R.id.item_usersList_btn_delete)).perform(click())
        onView(withId(android.R.id.button1)).perform(click())
    }
}