package com.akexorcist.extralivedatatest.ui.sequentially

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.akexorcist.extralivedatatest.ui.sequentially.event.BaseEvent
import com.akexorcist.extralivedatatest.ui.sequentially.event.Step0Event
import com.akexorcist.extralivedatatest.ui.sequentially.event.Step1Event
import com.akexorcist.extralivedatatest.ui.sequentially.event.Step2Event

class AwesomeViewModel : ViewModel() {
    val eventLiveData = MutableLiveData<BaseEvent>()

    fun doStep0(name: String) {
        eventLiveData.value =
                Step0Event(name)
    }

    fun doStep1(address: String) {
        when (val event = eventLiveData.value) {
            is Step0Event -> eventLiveData.value =
                    Step1Event(
                        event.name,
                        address
                    )
            else -> eventLiveData.value = eventLiveData.value
        }
    }

    fun doStep2(gender: String) {
        when (val event = eventLiveData.value) {
            is Step1Event -> eventLiveData.value =
                    Step2Event(
                        event.name,
                        event.address,
                        gender
                    )
            else -> eventLiveData.value = eventLiveData.value
        }
    }

//    val data1LiveData = MutableLiveData<String>()
//    val data2LiveData = MutableLiveData<String>()
//    val data3LiveData = MutableLiveData<String>()
//    val data4LiveData = MutableLiveData<String>()
//    val data5LiveData = MutableLiveData<String>()
//    val data6LiveData = MutableLiveData<String>()
//    val data7LiveData = MutableLiveData<String>()
//    val data8LiveData = MutableLiveData<String>()
//    val data9LiveData = MutableLiveData<String>()
//    val data10LiveData = MutableLiveData<String>()
//
//    fun updateValue() {
//        data1LiveData.value = "Akexorcist"
//        data2LiveData.value = "Akexorcist"
//        data3LiveData.value = "Akexorcist"
//        data4LiveData.value = "Akexorcist"
//        data5LiveData.value = "Akexorcist"
//        data6LiveData.value = "Akexorcist"
//        data7LiveData.value = "Akexorcist"
//        data8LiveData.value = "Akexorcist"
//        data9LiveData.value = "Akexorcist"
//        data10LiveData.value = "Akexorcist"
//    }
}