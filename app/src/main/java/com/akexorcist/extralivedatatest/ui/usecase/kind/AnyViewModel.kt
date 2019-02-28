package com.akexorcist.extralivedatatest.ui.usecase.kind

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.akexorcist.extralivedatatest.domain.result.Result

class AnyViewModel constructor() : ViewModel() {
    private val simpleUseCase = AnySimpleUseCase()
    private val mediatorUseCase = AnyMediatorCallbackUseCase()

    private val _simple = MutableLiveData<Result<String>>()
    private val _simpleResult = MediatorLiveData<String>()
    // Observe this for UseCase from repository
    private val simpleResult: LiveData<String>
        get() = _simpleResult

    private val _mediator: LiveData<Result<String>>
    private val _mediatorResult = MediatorLiveData<String>()
    // Observe this for MediatorUseCase from repository
    private val mediatorResult: LiveData<String>
        get() = _mediatorResult

    init {
        _simpleResult.addSource(_simple) {
            (_simple.value as? Result.Success)?.data?.let { result: String ->
                _simpleResult.value = result
            }
        }

        _mediator = mediatorUseCase.observe()
        _mediatorResult.addSource(_mediator) {
            (_mediator.value as? Result.Success)?.data?.let {
                _mediatorResult.value = it
            }
        }
    }

    fun go() {
        simpleUseCase("Akexorcist", _simple)
        mediatorUseCase.execute("Akexorcist")
    }
}