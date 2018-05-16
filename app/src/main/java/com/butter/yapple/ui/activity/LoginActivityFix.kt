package com.butter.yapple.ui.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.text.TextUtils
import android.widget.Toast
import com.butter.yapple.R
import com.butter.yapple.ui.customview.MyProgressDialog
import com.butter.yapple.utils.AccountValidatorUtil
import com.butter.yapple.utils.ComUtils
import com.butter.yapple.utils.Constant
import kotlinx.android.synthetic.main.activity_login_fix.*
import java.util.*

class LoginActivityFix : AppCompatActivity() {
    lateinit var dialog: MyProgressDialog
    val context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_fix)
        bt_login.setOnClickListener {
            val email = et_email.text.toString().trim()
            val password = et_pwd.text.toString().trim()
            if (!TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                if (AccountValidatorUtil.isEmail(email)) {
                    if (AccountValidatorUtil.isPassword(password)) {
                        dialog = MyProgressDialog(this)
                        dialog.initDialog("")
                        //发送延迟请求
                        val t = Random().nextInt(3)
                        val msg = Message()
                        msg.what = 0
                        val bundle: Bundle = Bundle()
                        bundle.putString("email", email)
                        bundle.putString("password", password);
                        msg.data = bundle
                        handler.sendMessageDelayed(msg, 1000 * t.toLong())
                    } else {
                        Toast.makeText(this, "密码格式必须为英文或数字，6-20位", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "邮箱格式不正确", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "邮箱或密码不能为空", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun toLogin(mEmail: String, mPassword: String): Boolean {
        if (mEmail.equals(Constant.DEFAULT_EMAIL) && mPassword.equals(Constant.DEFAULT_PWD)) {
            ComUtils.saveLoginInfo(this, mEmail, mPassword)
            return true
        } else {
            val users = ComUtils.getRegisterUserList(context)
            users.forEach {
                if (it.email.equals(mEmail)) {
                    if (it.pwd.equals(mPassword)) {
                        ComUtils.saveLoginInfo(context, mEmail, mPassword)
                        return true
                    } else {
                        return false
                    }

                }
            }
            ComUtils.saveToRegisterUserList(context, mEmail, mPassword)
            ComUtils.saveLoginInfo(context, mEmail, mPassword)
            return true

        }
        return false
    }

    val handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when (msg?.what) {
                0 -> {
                    dialog.dissmisDialog()
                    if (toLogin(msg.data.getString("email"), msg.data.getString("password"))) {
                        startActivity(Intent(context, MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(context, "登陆失败", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
