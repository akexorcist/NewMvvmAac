package com.akexorcist.extralivedatatest.ui.usecase.simple

import com.akexorcist.extralivedatatest.domain.UseCase
import com.akexorcist.extralivedatatest.vo.User

open class GetSimpleDataUseCase constructor() : UseCase<String, User>() {
    override fun execute(parameters: String): User {
        return User(parameters, "Home", "Male")
    }
}