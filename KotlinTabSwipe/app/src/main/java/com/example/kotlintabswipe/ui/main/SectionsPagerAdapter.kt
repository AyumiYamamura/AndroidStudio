package com.example.kotlintabswipe.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.kotlintabswipe.R

private val TAB_TITLES = arrayOf(
        R.string.tab_text_1,
        R.string.tab_text_2
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(fm: FragmentManager,
                           tabsFragments : ArrayList<Class<out Fragment>>) : FragmentPagerAdapter(fm) {
    val tabsFragments: ArrayList<Class<out Fragment>> = tabsFragments
    override fun getItem(position: Int): Fragment {
        return tabsFragments[position].newInstance()
    }
    override fun getCount(): Int {
        return tabsFragments.size
    }

}