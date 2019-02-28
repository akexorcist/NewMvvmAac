package com.akexorcist.extralivedatatest.ui.usecase.simple

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.akexorcist.extralivedatatest.domain.result.Result
import com.akexorcist.extralivedatatest.map
import com.akexorcist.extralivedatatest.vo.User

class SimpleDataViewModel constructor() : ViewModel() {
    // Should be injected
    private val useCase = GetSimpleDataUseCase()

    private val _user = MutableLiveData<Result<User>>()
//    val user: LiveData<Result<User>>
//        get() = _user

    private val _data = MediatorLiveData<User>()
    val data: LiveData<User>
        get() = _data

    val isAkexorcist: LiveData<Boolean> = _data.map { data -> data.name == "Akexorcist" }

    init {
        _data.addSource(_user) {
            (_user.value as? Result.Success)?.data?.let { user ->
                _data.value = user
            }
        }
    }

    fun getData() {
        useCase("Akexorcist", _user)
    }
}