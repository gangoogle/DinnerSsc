package com.butter.dinnerssc.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.butter.dinnerssc.R
import com.butter.dinnerssc.model.event.FinishEvent
import com.butter.dinnerssc.ui.activity.CollectionActivity
import com.butter.dinnerssc.ui.activity.FeedBackActivity
import com.butter.dinnerssc.ui.activity.LoginActivity
import com.butter.dinnerssc.utils.ComUtils
import org.greenrobot.eventbus.EventBus
import kotlinx.android.synthetic.main.fragment_me.view.*
/**
 * Created by gangoogle on 2018/3/19.
 */
class MeFragment :BaseFragment(){

    init {
    }
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_me,container,false)
    }
    override fun initViewData() {

        mView?.tv_un_register?.setOnClickListener {
            ComUtils.saveLoginInfo(context, "", "")
            context.startActivity(Intent(context, LoginActivity::class.java))
            EventBus.getDefault().post(FinishEvent(true))
        }
        //eamil
        mView?.tv_user_eamil?.text = ComUtils.getLoginInfo(context).email
        //收藏
        mView?.tv_collection?.text = "${ComUtils.getCollections(context)?.size ?: 0}条"
        //返回按钮
//        iv_back.setOnClickListener { finish() }


        mView?.tv_notification_switch?.text = if (ComUtils.getNotificationSetting(context)) "开" else "关"
        mView?.tv_notification_switch?.setOnClickListener {
            ComUtils.setNotificationSetting(context, !ComUtils.getNotificationSetting(context))
            mView?.tv_notification_switch?.text = if (ComUtils.getNotificationSetting(context)) "开" else "关"
        }

        mView?.tv_score?.setText("${ComUtils.getScore(context)} C")

        mView?.ll_collection?.setOnClickListener {
            context.startActivity(Intent(context, CollectionActivity::class.java))
        }

        mView?.ll_suggest?.setOnClickListener {
            context.startActivity(Intent(context, FeedBackActivity::class.java))
        }
    }

    override fun setClick(view: View?) {
    }

}