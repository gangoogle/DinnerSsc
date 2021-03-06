package com.butter.yapplet.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.butter.yapplet.R
import com.butter.yapplet.ui.adapter.RCCollectionAdapter
import com.butter.yapplet.utils.ComUtils
import kotlinx.android.synthetic.main.activity_collection.*

class CollectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collection)
        rc_view.layoutManager = LinearLayoutManager(this)
        rc_view.adapter = RCCollectionAdapter(this, ComUtils.getCollections(this))
        iv_back_a.setOnClickListener{finish()}
    }
}
