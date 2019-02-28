package com.akexorcist.extralivedatatest.ui.sequentially

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.akexorcist.extralivedatatest.R
import com.akexorcist.extralivedatatest.ui.sequentially.event.BaseEvent
import com.akexorcist.extralivedatatest.ui.sequentially.event.Step0Event
import com.akexorcist.extralivedatatest.ui.sequentially.event.Step1Event
import com.akexorcist.extralivedatatest.ui.sequentially.event.Step2Event
import kotlinx.android.synthetic.main.activity_sequentially_live_data_event.*

class SequentiallyLiveDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sequentially_live_data_event)
        val viewModel = ViewModelProviders.of(this).get(AwesomeViewModel::class.java)
        viewModel.eventLiveData.observe(this, Observer<BaseEvent> { value ->
            when (value) {
                is Step0Event -> doStep0(value)
                is Step1Event -> doStep1(value)
                is Step2Event -> doStep2(value)
            }
        })
//        viewModel.data1LiveData.observe(this, Observer<String> { value ->
//            Log.e("Check", "Data 1 : $value")
//        })
//        viewModel.data2LiveData.observe(this, Observer<String> { value ->
//            Log.e("Check", "Data 2 : $value")
//        })
//        viewModel.data3LiveData.observe(this, Observer<String> { value ->
//            Log.e("Check", "Data 3 : $value")
//        })
//        viewModel.data4LiveData.observe(this, Observer<String> { value ->
//            Log.e("Check", "Data 4 : $value")
//        })
//        viewModel.data5LiveData.observe(this, Observer<String> { value ->
//            Log.e("Check", "Data 5 : $value")
//        })
//        viewModel.data6LiveData.observe(this, Observer<String> { value ->
//            Log.e("Check", "Data 6 : $value")
//        })
//        viewModel.data8LiveData.observe(this, Observer<String> { value ->
//            Log.e("Check", "Data 8 : $value")
//        })
//        viewModel.data7LiveData.observe(this, Observer<String> { value ->
//            Log.e("Check", "Data 7 : $value")
//        })
//        viewModel.data9LiveData.observe(this, Observer<String> { value ->
//            Log.e("Check", "Data 9 : $value")
//        })
//        viewModel.data10LiveData.observe(this, Observer<String> { value ->
//            Log.e("Check", "Data 10 : $value")
//        })

        buttonGoStep0.setOnClickListener {
            //            viewModel.updateValue()
            viewModel.doStep0("Akexorcist")
        }

        buttonGoStep1.setOnClickListener {
            //            viewModel.updateValue()
            viewModel.doStep1("Nextzy")
        }

        buttonGoStep2.setOnClickListener {
            //            viewModel.updateValue()
            viewModel.doStep2("Male")
        }
        prepare()
    }

    private fun prepare() {
        buttonGoStep0.visibility = View.VISIBLE
        buttonGoStep1.visibility = View.INVISIBLE
        buttonGoStep2.visibility = View.INVISIBLE
        buttonGoStep3.visibility = View.INVISIBLE
    }

    private fun doStep0(event: Step0Event) {
        buttonGoStep0.visibility = View.INVISIBLE
        buttonGoStep1.visibility = View.VISIBLE
        buttonGoStep2.visibility = View.INVISIBLE
        buttonGoStep3.visibility = View.INVISIBLE
        Log.e("Check Step0", "Name : ${event.name}")
    }

    private fun doStep1(event: Step1Event) {
        buttonGoStep0.visibility = View.INVISIBLE
        buttonGoStep1.visibility = View.INVISIBLE
        buttonGoStep2.visibility = View.VISIBLE
        buttonGoStep3.visibility = View.INVISIBLE
        Log.e("Check Step1", "Name : ${event.name}")
        Log.e("Check Step1", "Address : ${event.address}")
    }

    private fun doStep2(event: Step2Event) {
        buttonGoStep0.visibility = View.INVISIBLE
        buttonGoStep1.visibility = View.INVISIBLE
        buttonGoStep2.visibility = View.INVISIBLE
        buttonGoStep3.visibility = View.VISIBLE
        Log.e("Check Step2", "Name : ${event.name}")
        Log.e("Check Step2", "Address : ${event.address}")
        Log.e("Check Step2", "Gender : ${event.gender}")
    }
}
