package com.butter.yapplet.ui.activity

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.butter.yapplet.R
import com.butter.yapplet.data.getTrendItemData
import com.butter.yapplet.model.event.*
import com.butter.yapplet.ui.customview.MyProgressDialog
import com.butter.yapplet.ui.fragment.*

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_dimens.*
import kotlinx.android.synthetic.main.view_main_toolbar.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var fragments: MutableList<BaseHomeFragment>
    private val OVERVIEW_INDEX = 0
    private val SOCCER_INDEX = 1
    private val CHART_INDEX = 2
    private val ME_INDEX = 3
    lateinit var dialog: MyProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
        setContentView(R.layout.activity_main)
        tv_titlea.text = getTrendItemData()[0].name
        init()
    }

    private fun init() {
        setClick()
        setToolBarStatus(0)
        dialog = MyProgressDialog(this)
        free_vp.setBackgroundColor(Color.LTGRAY)
        //缓存3个页面
        free_vp.offscreenPageLimit=4
        free_vp.setScrollable(false)
        fragments = ArrayList<BaseHomeFragment>()
        //TODO add fragments 2 the vp....
        fragments.add(HomeMainFragment())
        fragments.add(HomeSoccerFragment())
        fragments.add(HomeChartFragment())
        fragments.add(HomeMeFragment())
        free_vp.setAdapter(FragmentAdapter(supportFragmentManager, fragments))
        //发送延迟请求
        val t = Random().nextInt(3)
        dialog.initDialog("")
        val msg = Message()
        msg.what = 0
        handler.sendMessageDelayed(msg, 1000 * (t.toLong() + 1))
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onReEventFinish(event: FinishEvent) {
        finish()
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
        rl_chart.setOnClickListener{
            EventBus.getDefault().post(InitThreePape(""))
            free_vp.setCurrentItem(CHART_INDEX)
            setToolBarStatus(CHART_INDEX)
        }
        rl_soccer.setOnClickListener {
            EventBus.getDefault().post(InitTwoPape(""))
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
        iv_chart.isSelected=position==CHART_INDEX
        tv_charta.isSelected=position==CHART_INDEX
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
        tv_titlea.text = getTrendItemData()[event.position].name
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}

