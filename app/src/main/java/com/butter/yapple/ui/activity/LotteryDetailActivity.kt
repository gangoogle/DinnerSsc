package com.butter.yapple.ui.activity

import android.content.Context
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.butter.yapple.net.RetrofitNetHelper
import com.butter.yapple.R
import com.butter.yapple.model.response.BaseResp
import com.butter.yapple.model.response.CaiPiaoResponse
import com.butter.yapple.model.response.CaiPiaoUrl
import com.butter.yapple.net.Api
import com.butter.yapple.ui.adapter.RCLottertAdapter
import com.butter.yapple.ui.customview.MyProgressDialog
import com.butter.yapple.utils.ComUtils
import kotlinx.android.synthetic.main.activity_lottery_detail.*

class LotteryDetailActivity : AppCompatActivity() {
    lateinit var mContext: Context
    lateinit var dialog: MyProgressDialog
    lateinit var mCurrCaipiaoUrl: String
    lateinit var mCaipiaoUrlList: List<CaiPiaoUrl>
    val SIZE = 20
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lottery_detail)
        mContext = this
        dialog = MyProgressDialog(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ll_status_l.setBackgroundColor(this.resources.getColor(R.color.olivedrab))
            this.getWindow().setStatusBarColor(this.resources.getColor(R.color.olivedrab))
            tv_title_l.setTextColor(this.resources.getColor(R.color.white))
        }
        mCurrCaipiaoUrl = intent.getStringExtra("url")
        tv_title_l?.text = intent.getStringExtra("name")
        fab_l.setOnClickListener {
            finish()
        }
        reqquestLottery()
        ComUtils.addScore(this, 2)
    }


    private fun reqquestLottery() {
        dialog.initDialog("loading...")
        val callResponse = RetrofitNetHelper.getInstance(mContext)
                .getAPIService(Api::class.java)
                .requestUrl("${mCurrCaipiaoUrl}-$SIZE.json")
        RetrofitNetHelper.getInstance(mContext)
                .enqueueCall(callResponse, object : RetrofitNetHelper.RetrofitCallBack<CaiPiaoResponse> {
                    override fun onSuccess(baseResp: BaseResp<CaiPiaoResponse>) {
                        dialog.dissmisDialog()
                        if(mContext!=null&&!isFinishing) {
                            rc_view_l.layoutManager = LinearLayoutManager(mContext)
                            rc_view_l.adapter = RCLottertAdapter(mContext, baseResp.data)
                        }
                    }

                    override fun onFailure(error: String) {
                        dialog.dissmisDialog()
                        Toast.makeText(mContext, "数据拉取失败", Toast.LENGTH_SHORT).show()
                    }
                })
    }

}
