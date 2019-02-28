package com.akexorcist.extralivedatatest.ui.usecase.kind

import android.arch.lifecycle.LiveData
import android.os.Handler
import com.akexorcist.extralivedatatest.domain.MediatorUseCase
import com.akexorcist.extralivedatatest.domain.result.Result

class AnyMediatorLiveDataUseCase constructor() : MediatorUseCase<String, String>() {
    override fun execute(parameters: String) {
        val repository = SimpleRepository()
        // Avoid duplicate observe
        result.removeSource(repository)
        result.value = null
        // Get started!
        result.addSource(repository) { data: String? ->
            data?.let {
                result.postValue(Result.Success(it))
            }
        }
    }

    fun onCleared() {
        // Remove any subscription to repository live data
    }
}

class SimpleRepository : LiveData<String>() {
    override fun onActive() {
        super.onActive()
        doSomething()
    }

    private fun doSomething() {
        Handler().postDelayed({
            value = "Akexorcist"
        }, 1000)
    }
}