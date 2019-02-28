package com.akexorcist.extralivedatatest.ui.usecase.kind

import android.os.Handler
import com.akexorcist.extralivedatatest.domain.MediatorUseCase
import com.akexorcist.extralivedatatest.domain.result.Result

class AnyMediatorCallbackUseCase constructor() : MediatorUseCase<String, String>() {
    override fun execute(parameters: String) {
        Handler().postDelayed({
            result.postValue(Result.Success(parameters))
        }, 1000)
    }
}