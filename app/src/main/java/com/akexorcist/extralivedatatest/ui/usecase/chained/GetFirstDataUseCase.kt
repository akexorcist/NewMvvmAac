package com.akexorcist.extralivedatatest.ui.usecase.chained

import com.akexorcist.extralivedatatest.domain.UseCase
import com.akexorcist.extralivedatatest.vo.User

open class GetFirstDataUseCase constructor() : UseCase<String, User>() {
    override fun execute(parameters: String): User {
        // Get data from api
        return User(parameters, "Home", "Male")
    }
}