package com.akexorcist.extralivedatatest.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.akexorcist.extralivedatatest.R
import com.akexorcist.extralivedatatest.ui.sequentially.SequentiallyLiveDataActivity
import com.akexorcist.extralivedatatest.ui.usecase.chained.ChainedDataUseCaseActivity
import com.akexorcist.extralivedatatest.ui.usecase.simple.SimpleDataUseCaseActivity
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        buttonHandleSequentiallyLiveDataEvent.setOnClickListener {
            startActivity(Intent(this, SequentiallyLiveDataActivity::class.java))
        }
        buttonSimpleLogicWithUseCase.setOnClickListener {
            startActivity(Intent(this, SimpleDataUseCaseActivity::class.java))
        }
        buttonChainedLogicWithUseCase.setOnClickListener {
            startActivity(Intent(this, ChainedDataUseCaseActivity::class.java))
        }
    }
}
