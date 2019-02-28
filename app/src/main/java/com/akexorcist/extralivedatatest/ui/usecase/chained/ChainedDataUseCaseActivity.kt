package com.akexorcist.extralivedatatest.ui.usecase.chained

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.akexorcist.extralivedatatest.R
import com.akexorcist.extralivedatatest.vo.User
import kotlinx.android.synthetic.main.activity_chained_data_use_case.*

class ChainedDataUseCaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chained_data_use_case)
        val viewModel = ViewModelProviders.of(this).get(ChainedDataViewModel::class.java)
        viewModel.firstDataResult.observe(this, Observer<User> { user: User? ->
            Log.e("Check", "Hello, ${user?.name}")
        })
        viewModel.secondDataResult.observe(this, Observer<Boolean> { isAkexorcist: Boolean? ->
            Log.e("Check", "Is Akexorcist? $isAkexorcist")
        })
        viewModel.showLoading.observe(this, Observer<Unit> {
            // Show loading
        })
        buttonGo.setOnClickListener {
            // Call any asynchronous code
            viewModel.getData()
        }
    }
}
