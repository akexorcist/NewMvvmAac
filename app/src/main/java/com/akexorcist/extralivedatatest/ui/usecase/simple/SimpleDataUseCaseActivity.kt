package com.akexorcist.extralivedatatest.ui.usecase.simple

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.akexorcist.extralivedatatest.R
import com.akexorcist.extralivedatatest.vo.User
import kotlinx.android.synthetic.main.activity_simple_data_use_case.*

class SimpleDataUseCaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_data_use_case)

        val viewModel = ViewModelProviders.of(this).get(SimpleDataViewModel::class.java)
        viewModel.data.observe(this, Observer<User> { user: User? ->
            Log.e("Check", "Something is coming now ${user?.name}")
        })
//        viewModel.user.observe(this, Observer<Result<User>> { result: Result<User>? ->
//            (result as? Result.Success)?.data?.let { data ->
//                val (name, address, gender) = data
//                // Do something when get data successfully
//            }
//            (result as? Result.Error)?.exception?.let { exception ->
//                // Do something when get any exception
//            }
//            (result as? Result.Loading)?.let {
//                // Do something when loading
//            }
//        })
        viewModel.isAkexorcist.observe(this, Observer<Boolean> { isAkexorcist ->
            // Do something when this guy is Akexorcist or not
            // This LiveData has mapped from `data` Live Data for secondary UI objective
        })
        buttonGo.setOnClickListener {
            viewModel.getData()
        }
    }
}
