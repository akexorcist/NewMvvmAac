package com.akexorcist.extralivedatatest.ui.usecase.chained

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.akexorcist.extralivedatatest.domain.result.Result
import com.akexorcist.extralivedatatest.then
import com.akexorcist.extralivedatatest.vo.User

class ChainedDataViewModel constructor() : ViewModel() {
    // Should be injected
    private val firstUseCase = GetFirstDataUseCase()
    private val secondUseCase = GetSecondDataUseCase()

    private val _firstData = MutableLiveData<Result<User>>()

    // Result of Use Case 1
    private val _firstDataResult = MediatorLiveData<User>()
    // If you want first result, observe this
    val firstDataResult: LiveData<User>
        get() = _firstDataResult

    // Result of Use Case 2
    private val _secondData: MediatorLiveData<Result<Boolean>>

    private val _secondDataResult = MediatorLiveData<Boolean>()
    // If you want second result, observe this
    val secondDataResult: LiveData<Boolean>
        get() = _secondDataResult

    private val _showLoading = MediatorLiveData<Unit>()
    // If you want second result, observe this
    val showLoading: LiveData<Unit>
        get() = _showLoading

    init {
        _secondData = secondUseCase.observe()

        _firstData.then(_firstDataResult) {
            // Convert
            if (it is Result.Success) {
                (it as? Result.Success)?.data?.let { value ->
                    _firstDataResult.value = value
                }
            } else if (it is Result.Loading) {
                _showLoading.value = Unit
            }
        }

        _secondData.then(_secondDataResult) {
            // Convert
            (it as? Result.Success)?.data?.let { isAkexorcist ->
                _secondDataResult.value = isAkexorcist
            }
        }

        _firstData.then(_secondData) {
            // Chained Execute
            (it as? Result.Success)?.data?.let { value ->
                // Execute the code in chained use cae
                secondUseCase.execute(value)
            }
        }
    }

    override fun onCleared() {
        secondUseCase.onCleared()
    }

    fun getData() {
        // Should be injected
        firstUseCase("Akexorcist", _firstData)
    }
}