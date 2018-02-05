package com.butter.dinnerssc.ui.activity

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.butter.dinnerssc.R
import com.butter.dinnerssc.ui.fragment.BaseHomeFragment
import com.butter.dinnerssc.ui.fragment.HomeMainFragment
import com.butter.dinnerssc.ui.fragment.HomeMeFragment
import java.util.ArrayList

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_main_toolbar.*

class MainActivity : AppCompatActivity() {
    private lateinit var fragments: MutableList<BaseHomeFragment>
    private val OVERVIEW_INDEX = 0
    private val ME_INDEX = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        setClick()
        setToolBarStatus(0)
        free_vp.setBackgroundColor(Color.LTGRAY)
        free_vp.setScrollable(false)
        fragments = ArrayList<BaseHomeFragment>()
        //TODO add fragments 2 the vp....
        fragments.add(HomeMainFragment())
        fragments.add(HomeMeFragment())
        free_vp.setAdapter(FragmentAdapter(supportFragmentManager, fragments))
    }

    private fun setClick() {
        rl_overview.setOnClickListener {
            free_vp.setCurrentItem(OVERVIEW_INDEX)
            setToolBarStatus(OVERVIEW_INDEX)
        }
        rl_me.setOnClickListener {
            free_vp.setCurrentItem(ME_INDEX)
            setToolBarStatus(ME_INDEX)
        }
    }

    private fun setToolBarStatus(position: Int) {
        iv_overview.isSelected = position == OVERVIEW_INDEX
        tv_overview.isSelected = position == OVERVIEW_INDEX
        iv_me.isSelected = position == ME_INDEX
        tv_me.isSelected = position == ME_INDEX
    }


    internal inner class FragmentAdapter(fm: FragmentManager, private val fragments: List<BaseHomeFragment>) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return fragments.size
        }
    }
}

