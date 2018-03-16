package com.butter.dinnerssc.ui.activity

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.butter.dinnerssc.R
import com.butter.dinnerssc.data.getTrendItemData
import com.butter.dinnerssc.model.event.InitDataEvent
import com.butter.dinnerssc.model.event.SwitchLotteryEvent
import com.butter.dinnerssc.ui.customview.MyProgressDialog
import com.butter.dinnerssc.ui.fragment.BaseHomeFragment
import com.butter.dinnerssc.ui.fragment.HomeMainFragment
import com.butter.dinnerssc.ui.fragment.HomeMeFragment
import com.butter.dinnerssc.ui.fragment.HomeSoccerFragment

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_main_toolbar.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var fragments: MutableList<BaseHomeFragment>
    private val OVERVIEW_INDEX = 0
    private val SOCCER_INDEX = 1
    private val ME_INDEX = 2
    lateinit var dialog: MyProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
        setContentView(R.layout.activity_main)
        tv_title.text = getTrendItemData()[0].name
        init()
    }

    private fun init() {
        setClick()
        setToolBarStatus(0)
        dialog = MyProgressDialog(this)
        free_vp.setBackgroundColor(Color.LTGRAY)
        //缓存3个页面
        free_vp.offscreenPageLimit=3
        free_vp.setScrollable(false)
        fragments = ArrayList<BaseHomeFragment>()
        //TODO add fragments 2 the vp....
        fragments.add(HomeMainFragment())
        fragments.add(HomeSoccerFragment())
        fragments.add(HomeMeFragment())
        free_vp.setAdapter(FragmentAdapter(supportFragmentManager, fragments))
        //发送延迟请求
        val t = Random().nextInt(3)
        dialog.initDialog("")
        val msg = Message()
        msg.what = 0
        handler.sendMessageDelayed(msg, 1000 * (t.toLong() + 1))
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
        rl_soccer.setOnClickListener {
            free_vp.setCurrentItem(SOCCER_INDEX)
            setToolBarStatus(SOCCER_INDEX)
        }
    }

    private fun setToolBarStatus(position: Int) {
        iv_overview.isSelected = position == OVERVIEW_INDEX
        tv_overview.isSelected = position == OVERVIEW_INDEX
        iv_me.isSelected = position == ME_INDEX
        tv_me.isSelected = position == ME_INDEX
        iv_soccer.isSelected = position == SOCCER_INDEX
        tv_soccer.isSelected = position == SOCCER_INDEX
    }


    internal inner class FragmentAdapter(fm: FragmentManager, private val fragments: List<BaseHomeFragment>) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return fragments.size
        }
    }


    val handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when (msg?.what) {
                0 -> {
                    EventBus.getDefault().post(InitDataEvent(""))
                    dialog.dissmisDialog()
                }
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun switchLotteryEvent(event: SwitchLotteryEvent) {
        tv_title.text = getTrendItemData()[event.position].name
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}

