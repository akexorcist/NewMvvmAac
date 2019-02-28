package com.akexorcist.extralivedatatest.ui.usecase.chained

import com.akexorcist.extralivedatatest.domain.MediatorUseCase
import com.akexorcist.extralivedatatest.domain.result.Result
import com.akexorcist.extralivedatatest.vo.User

open class GetSecondDataUseCase constructor() : MediatorUseCase<User, Boolean>() {
    override fun execute(parameters: User) {
//        result.removeSource(/* any live data from repository */)
//        result.value = null // To make sure that live data was cleared before do next thing
//        result.addSource(/* any live data from repository */)
        // Do anything as you want with live data or not just postValue(..) to emit the data to view model
        result.postValue(Result.Success(parameters.name == "Akexorcist"))
    }

    fun onCleared() {
        // This use case is no longer going to be used so remove any subscriptions if you have
    }
}