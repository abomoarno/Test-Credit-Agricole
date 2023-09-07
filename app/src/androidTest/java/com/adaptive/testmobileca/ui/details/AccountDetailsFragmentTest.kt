package com.adaptive.testmobileca.ui.details

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.adaptive.testmobileca.R
import com.adaptive.testmobileca.domaine.Bank
import com.adaptive.testmobileca.utils.AssetsUtils
import com.google.gson.Gson
import org.junit.Assert.*
import org.junit.Test
import kotlin.random.Random

class AccountDetailsFragmentTest{


    @Test
    fun test_fragment_displayed(){

        val json = AssetsUtils.getJsonDataFromAsset(ApplicationProvider.getApplicationContext())

        val banks: ArrayList<Bank> = Gson().fromJson(json, Array<Bank>::class.java).toList() as ArrayList<Bank>

        val account = banks[Random(0).nextInt(banks.size)].bankAccounts[Random(0).nextInt(banks[0].bankAccounts.size)]

        val scenario = launchFragmentInContainer<AccountDetailsFragment>(
            fragmentArgs = bundleOf(
                AccountDetailsFragment.ARG_ACCOUNT to account
            )
        )

        onView(withId(R.id.tv_account_balance)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_operations)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_account_balance)).check(matches(withText("${account.balance} €")))
        onView(withId(R.id.tv_account_label)).check(matches(withText(account.label)))
    }

}