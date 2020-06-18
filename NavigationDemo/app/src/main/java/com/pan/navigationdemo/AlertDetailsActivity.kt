package com.pan.navigationdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.findNavController
import androidx.navigation.navArgs
import kotlinx.android.synthetic.main.alertdetails_act.*

/**
 * 通知打开后的页面
 */
class AlertDetailsActivity : AppCompatActivity() {

    private val args by navArgs<AlertDetailsActivityArgs>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.alertdetails_act)
//        textView.text = intent.getStringExtra("textView")
        textView.text = args.textView
    }
}